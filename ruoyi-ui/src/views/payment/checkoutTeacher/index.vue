<template>
  <div>
    <el-row>
      <el-col :span="10" style="margin-left:15px">
        <div style="text-align: left" v-for="i in teacherList" :key="i.teacherId">
          <el-card class="box-card" shadow="always" style="margin: 6px">
            <div slot="header" class="clearfix">
              <span>{{ i.teacherName }}</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click.native="showTeacherSalary(i.teacherId,i.teacherName,i.teacherEmployType)">查看详情</el-button>
            </div>
            <div>
              用工类别：<el-tag v-show="i.teacherEmployType === 0" type="info">兼职</el-tag><el-tag v-show="i.teacherEmployType === 1">全职</el-tag><el-tag type="success" v-show="i.teacherEmployType === 2">实习生</el-tag><el-tag v-show="i.teacherEmployType === 3" type="warning">暑假工</el-tag>
              <div style="float:right;">
                在职状态：<el-tag v-show="i.teacherStatus === 0">在职</el-tag><el-tag v-show="i.teacherStatus === 1" type="warning">离职</el-tag>
              </div>
            </div>
            <div>
              联系方式：{{ i.teacherPhone }}
            </div>
          </el-card>
        </div>
        <pagination
          v-show="teacherTotal>0"
          :total="teacherTotal"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="initTeachers"
        />
        <br/>
        <br/>
      </el-col>
      <el-col :span="13" v-loading="checkoutTeacherLoading">
        <el-row>
          <el-table :data="teacherSalaryList" :row-class-name="tableRowClassName">
            <el-table-column label="员工姓名" align="center" prop="teacherName">
              <template slot-scope="scope">
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click">
                  <div>
                    <el-descriptions title="详细信息">
                      <el-descriptions-item label="教师ID">{{scope.row.teacherId}}</el-descriptions-item>
                      <el-descriptions-item label="教师姓名" :span="2">{{scope.row.teacherName}}</el-descriptions-item>
                      <el-descriptions-item label="考勤开始日期" :span="3">{{ parseTime(scope.row.checkInBeginDate, '{y}-{m}-{d}') }}</el-descriptions-item>
                      <el-descriptions-item label="考勤结束日期" :span="3">{{parseTime(scope.row.checkInEndDate, '{y}-{m}-{d}')}}</el-descriptions-item>
                      <el-descriptions-item label="考勤天数">{{scope.row.checkInTimes}}</el-descriptions-item>
                      <el-descriptions-item label="请假天数">{{scope.row.leaveDays}}</el-descriptions-item>
                      <el-descriptions-item label="值班次数">{{scope.row.onDutyTimes}}</el-descriptions-item>
                      <el-descriptions-item label="全勤补助">{{scope.row.fullCheckInPension || '无'}}</el-descriptions-item>
                      <el-descriptions-item label="其他补助">{{scope.row.otherPension}}</el-descriptions-item>
                      <el-descriptions-item label="其他扣除">{{scope.row.deduckMoney}}</el-descriptions-item>
                      <el-descriptions-item label="实际工资">{{scope.row.acutalSalary}}</el-descriptions-item>
                    </el-descriptions>
                  </div>
                  <el-button type="text" slot="reference">{{ scope.row.teacherName }}</el-button>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="实际工资" align="center" prop="acutalSalary" />
            <el-table-column label="考勤开始日期" align="center" prop="checkInBeginDate" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.checkInBeginDate, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="考勤结束日期" align="center" prop="checkInEndDate" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.checkInEndDate, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="teacherSalaryTotal>0"
            :total="teacherSalaryTotal"
            :page.sync="queryParams2.pageNum"
            :limit.sync="queryParams2.pageSize"
            @pagination="showTeacherSalary"
          />
        </el-row>
        <el-row>
          <div class="block" style="margin-top: 10px;text-align: center">
            <div v-if="teacher.teacherName">{{teacher.teacherName}} 详情</div>
            <div v-else>未选择老师</div>
            <br/>
            <span class="demonstration">日期范围：</span>
            <el-date-picker
              v-model="rangeDate"
              type="daterange"
              unlink-panels
              :disabled="!teacher.teacherName"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>&nbsp;
            <el-button type="primary" :disabled="!teacher.teacherName" round @click.native="calculateTeacherCheckin">统计考勤</el-button>

            <el-descriptions title="考勤信息" v-if="teacherSalaryBill">
              <el-descriptions-item label="员工">{{teacherSalaryBill.teacherName}}</el-descriptions-item>
              <el-descriptions-item label="考勤开始日期">{{ parseTime(rangeDate[0], '{y}-{m}-{d}') }}</el-descriptions-item>
              <el-descriptions-item label="考勤结束日期">{{ parseTime(rangeDate[1], '{y}-{m}-{d}') }}</el-descriptions-item>
              <el-descriptions-item label="考勤天数">{{teacherSalaryBill.checkInTimes}}</el-descriptions-item>
              <el-descriptions-item label="请假天数">{{teacherSalaryBill.leaveDays}}</el-descriptions-item>
              <el-descriptions-item label="值班次数">{{teacherSalaryBill.onDutyTimes}}</el-descriptions-item>
            </el-descriptions>
            <br/><br/>
            <div>
              薪资模板：
              <el-select v-model="salaryTemplateId" :disabled="!teacher.teacherName" placeholder="请选择薪资模板">
                <el-option
                  v-for="item in salaryTemplates"
                  :label="item.salaryTemplateName"
                  :value="item.salaryId">
                </el-option>
              </el-select>
            </div>
            <br/><br/>
            <el-row>
              <el-col :span="12">
                <div v-if="teacherSalaryBill">
                  <div>月工资：{{ salaryTemplate.salaryPerMonth }} 元 / 月
                    <el-radio-group v-model="salaryTemplate.salaryUsePerMonth" size="small" @change="verifyFeeUse(1)">
                      <el-radio :label="1">使用</el-radio>
                    </el-radio-group>
                  </div>
                  <div>日工资：{{ salaryTemplate.salaryPerDay }} 元 / 天
                    <el-radio-group v-model="salaryTemplate.salaryUserPerDay" size="small" @change="verifyFeeUse(0)">
                      <el-radio :label="1">使用</el-radio>
                    </el-radio-group>
                  </div>
                  <div>全勤工资：{{ salaryTemplate.salaryAllDuty }} 元 / 月
                    <el-radio-group v-model="allDuty" :disabled="0===salaryTemplate.salaryUsePerMonth" size="small" @change="calculateSumSalary">
                      <el-radio :label="1">全勤</el-radio>
                      <el-radio :label="0">未全勤</el-radio>
                    </el-radio-group>
                  </div>
                  <div>值班工资：{{ salaryTemplate.salaryOnDuty }} 元 / 次</div>
                  <div>请假扣除工资：{{ salaryTemplate.salaryOffDutyFee }} 元 / 天</div>
                  <div>其他补助：
                    <el-input style="width: 60%" v-model.number="otherPension" placeholder="请输入其他补助费用" @blur="calculateSumSalary"><template slot="append">元</template></el-input>
                  </div>
                  <div>其他扣除：
                    <el-input style="width: 60%" v-model.number="deduckMoney" placeholder="请输入扣除费用" @blur="calculateSumSalary"><template slot="append">元</template></el-input>
                  </div>
                  <div>
                  </div>
                </div>
              </el-col>
              <el-col :span="12">
                <div v-if="teacherSalaryBill">
                  <div v-if="1===salaryTemplate.salaryUsePerMonth">
                    {{ salaryTemplate.salaryPerMonth }} 月工资
                    <br/>
                     + {{ 1 === allDuty ? salaryTemplate.salaryAllDuty : 0 }} 全勤
                     - {{ teacherSalaryBill.leaveDays }} × {{salaryTemplate.salaryOffDutyFee}} 请假扣除
                  </div>
                  <div v-else>{{ teacherSalaryBill.checkInTimes }} × {{ salaryTemplate.salaryPerDay }} 日工资</div>
                  <div> + {{ teacherSalaryBill.onDutyTimes }} × {{salaryTemplate.salaryOnDuty}} 值班工资</div>
                  <div> + {{otherPension}} 补助</div>
                  <div> - {{deduckMoney}} 其他扣除</div>
                  <div>--------------------------------------------</div>
                  <el-button type="primary" @click="submitForm">结工资 {{salaryTemplate.acutalSalary || 0}} 元</el-button>
                </div>
            </el-col>
            </el-row>


          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { listTeachers, listTeacherSalary, getTeacherCheckinStatistic, listSalaryTemplate, addSalaryTemplate } from "@/api/payment/checkoutTeacher";
  import { parseTime } from '@/utils/ruoyi'
  export default {
    components: {},
    props: [],
    data() {
      return {
        checkoutTeacherLoading: false,
        allDuty: 0,
        rangeDate:'',
        queryParams2: {
          pageNum: 1,
          pageSize: 5,
          teacherId: null
        },
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        teacherSalaryList: [],
        teacherList: [],
        teacherTotal: 0,
        teacherSalaryTotal: 0,
        formData: {},
        rules: {},
        teacher: {
          teacherName: null,
          teacherId: null
        },
        teacherSalaryBill: null,
        salaryTemplates: [],
        salaryTemplateId: null,
        salaryTemplate: null,
        otherPension: 0,
        deduckMoney: 0,
      }
    },
    computed: {},
    watch: {
      salaryTemplateId: {
        handler(){
          this.showSalaryTemplateDetail();
        }
      }
    },
    created() {
      this.initTeachers();
      this.initSalaryTemplate();
    },
    mounted() {},
    methods: {
      verifyFeeUse(useNum){
        if (0 === useNum){
          this.salaryTemplate.salaryUsePerMonth = 0;
        } else {
          this.salaryTemplate.salaryUserPerDay = 0;
        }
        this.calculateSumSalary();
      },
      calculateSumSalary(){
        let acutalSalary = 0;
        if (this.teacherSalaryBill){
          if (1 === this.salaryTemplate.salaryUsePerMonth){
            //用月工资
            acutalSalary += this.salaryTemplate.salaryPerMonth;
            if (null === this.teacherSalaryBill.leaveDays || 0 === this.teacherSalaryBill.leaveDays){
              // 没请假就全勤
              if (1 === this.allDuty){
                acutalSalary += this.salaryTemplate.salaryAllDuty;
              }
            } else {
              // 请假扣请假工资
              acutalSalary -= this.salaryTemplate.salaryOffDutyFee * this.teacherSalaryBill.leaveDays;
            }
          } else {
            // 用日工资
            acutalSalary += this.salaryTemplate.salaryPerDay * this.teacherSalaryBill.checkInTimes;
          }
          // 值班补助
          acutalSalary += this.salaryTemplate.salaryOnDuty * this.teacherSalaryBill.onDutyTimes;
          // 其他补助和扣除
          acutalSalary += this.otherPension;
          acutalSalary -= this.deduckMoney;
          this.$set(this.salaryTemplate,'acutalSalary',acutalSalary);
        }

      },
      showSalaryTemplateDetail(){
        this.salaryTemplates.forEach(element => {
          if (element.salaryId === this.salaryTemplateId){
            this.salaryTemplate = element;
            this.calculateSumSalary();
          }
        });
      },
      calculateTeacherCheckin(){
        if ('' === this.rangeDate){
          this.$message.warning("没有选择统计日期范围");
          return;
        } else {
          let param = {
            teacherId: this.teacher.teacherId,
            teacherName: this.teacher.teacherName,
            checkInBeginDate: this.rangeDate[0].toLocaleString(),
            checkInEndDate: this.rangeDate[1].toLocaleString()
          }
          getTeacherCheckinStatistic(param).then(response => {
            this.teacherSalaryBill = response.data;
            this.calculateSumSalary();
          });
        }
      },
      showTeacherSalary(teacherId,teacherName,teacherEmployType){
        this.queryParams2.teacherId = teacherId;
        this.teacher.teacherId = teacherId;
        this.teacher.teacherName = teacherName;
        listTeacherSalary(this.queryParams2).then(response => {
          this.teacherSalaryList = response.rows;
          this.teacherSalaryTotal = response.total;
        });
        if ('' !== this.rangeDate){
          this.calculateTeacherCheckin();
        }
        this.queryTeacherEmployTypeForSalaryTemplate(teacherEmployType);
      },
      queryTeacherEmployTypeForSalaryTemplate(teacherEmployType){
        this.salaryTemplates.forEach(element => {
          if (element.salaryEmployType === teacherEmployType){
            this.salaryTemplateId = element.salaryId;
          }
        });
      },
      initTeachers(){
        listTeachers(this.queryParams).then(response => {
          this.teacherList = response.rows;
          this.teacherTotal = response.total;
        });
      },
      initSalaryTemplate(){
        listSalaryTemplate().then(response => {
          this.salaryTemplates = response.data;
        });
      },
      submitForm() {
        this.checkoutTeacherLoading = true;
        let teacherAttendance = {
          teacherId: this.teacher.teacherId,          // 教师id
          teacherName: this.teacher.teacherName,          // 教师姓名
          checkInBeginDate: parseTime(this.rangeDate[0], '{y}-{m}-{d}'),   // 考勤开始日期
          checkInEndDate: parseTime(this.rangeDate[1], '{y}-{m}-{d}'),     // 考勤结束日期
          checkInTimes: this.teacherSalaryBill.checkInTimes,       // 考勤次数
          leaveDays: this.teacherSalaryBill.leaveDays,          // 请假天数
          onDutyTimes: this.teacherSalaryBill.onDutyTimes,        // 值班次数
          onDutyPension: this.salaryTemplate.salaryOnDuty,      // 值班补助
          fullCheckIn: this.allDuty,        // 全勤（0全勤，1未全勤）
          fullCheckInPension: null, // 全勤奖
          otherPension: this.otherPension,       // 其他补助
          deduckMoney: this.salaryTemplate.salaryOffDutyFee * this.teacherSalaryBill.leaveDays + this.deduckMoney,        // 工资扣除
          acutalSalary: this.salaryTemplate.acutalSalary        // 实际工资
        };
        if (1 === this.allDuty){
          teacherAttendance.fullCheckInPension = this.salaryTemplate.salaryAllDuty;
        }
        addSalaryTemplate(teacherAttendance).then(response => {
          window.alert("结账成功")
          this.checkoutTeacherLoading = false;
          listTeacherSalary(this.queryParams2).then(response => {
            this.teacherSalaryList = response.rows;
            this.teacherSalaryTotal = response.total;
          });
          this.teacherSalaryBill = null;
          this.rangeDate = '';
        });
      },
      resetForm() {
        this.$refs['elForm'].resetFields()
      },
      tableRowClassName({row, rowIndex}) {
        if (rowIndex % 2 === 1) {
          return 'warning-row';
        } else if (rowIndex % 2 === 0) {
          return 'success-row';
        }
        return '';
      },
    }
  }

</script>
<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
