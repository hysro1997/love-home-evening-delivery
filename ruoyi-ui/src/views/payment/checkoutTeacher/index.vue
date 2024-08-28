<template>
  <div>
    <el-row>
      <el-col :span="8" style="margin-left:15px">
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
      </el-col>
      <el-col :span="15">
        <el-row>
          <el-table :data="teacherSalaryList" :row-class-name="tableRowClassName">
            <el-table-column label="员工姓名" align="center" prop="teacherName" />
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
              <el-select v-model="salaryTemplateId" placeholder="请选择薪资模板">
                <el-option
                  v-for="item in salaryTemplates"
                  :label="item.salaryTemplateName"
                  :value="item.salaryId">
                </el-option>
              </el-select>
            </div>
            <br/><br/>
            <div v-if="teacherSalaryBill">
              <div>月工资：{{ salaryTemplate.salaryPerMonth }} 元 / 月</div>
              <div>日工资：{{ salaryTemplate.salaryPerDay }} 元 / 天</div>
              <div>全勤工资：{{ salaryTemplate.salaryAllDuty }} 元 / 月</div>
              <div>值班工资：{{ salaryTemplate.salaryOnDuty }} 元 / 次</div>
              <div>请假扣除工资：{{ salaryTemplate.salaryOffDutyFee }} 元 / 天</div>
              <div>其他补助：元</div>
              <div>工资扣除：元</div>
            </div>
          </div>
        </el-row>
      </el-col>
    </el-row>

    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import { listTeachers, listTeacherSalary, getTeacherCheckinStatistic, listSalaryTemplate } from "@/api/payment/checkoutTeacher";
  export default {
    components: {},
    props: [],
    data() {
      return {
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
      showSalaryTemplateDetail(){
        this.salaryTemplates.forEach(element => {
          if (element.salaryId === this.salaryTemplateId){
            this.salaryTemplate = element;
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
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
        })
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
