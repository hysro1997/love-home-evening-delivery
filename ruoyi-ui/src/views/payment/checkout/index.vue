<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6" style="text-align: center"><h3>考勤总表</h3><br/>
        <div style="text-align: left" v-for="i in baseCheckinList" :key="i.baseCheckInId">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>{{ i.baseCheckInName }}</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click.native="showDetail(i.baseCheckInId,i.baseCheckInName)">查看详情</el-button>
              <el-button style="float: right; padding: 3px 0; margin-right: 10px" type="text" @click.native="calculateStatistic(i.baseCheckInId,i.baseCheckInName)">统计考勤数据</el-button>
            </div>
            <div>
              账单状态：<el-tag v-show="i.billStatus === 0" type="info">未生成</el-tag><el-tag v-show="i.billStatus === 1" type="warning">部分账单</el-tag><el-tag v-show="i.billStatus === 2">全部账单</el-tag>
            </div>
            <div>
              支付状态：<el-tag v-show="i.paymentStatus === 0" type="info">未支付</el-tag><el-tag v-show="i.paymentStatus === 1" type="warning">部分支付</el-tag><el-tag v-show="i.paymentStatus === 2">全部结清</el-tag>
            </div>
          </el-card>
        </div>
        <div style="text-align: right">
          <el-pagination style="margin-bottom:20px"
                         small
                         background
                         :pager-count="5"
                         v-show="total>0"
                         @current-change="getList"
                         :current-page.sync="queryParams.pageNum"
                         :page-size="10"
                         layout="total, prev, pager, next"
                         :total="total">
          </el-pagination>
        </div>
      </el-col>
      <el-col :span="14" style="margin-left: 15px;"><h3>选择年级</h3>
        <el-row>
          <el-radio-group v-model="grade" v-for="i in grades" @input="showDetailDetail()">
            <el-radio-button style="margin: 10px" :label="i.grade"></el-radio-button>
          </el-radio-group>
        </el-row>
        <h3>{{ baseCheckInName }} {{ grade }} 详情</h3>
        <br/>
        <div>如果没有数据，或数据与实际不符合，请单击左侧对应的 统计考勤数据 ，来获取真实统计结果</div>
        <el-row :v-loading="studentsLoading">
          <el-col :span="8" v-for="item in students">
            <el-card class="box-card" shadow="hover" style="width: 250px;height: 150px">
              <div slot="header" class="clearfix">
                <span>{{ item.studentName }}</span>
                <el-button v-show="item.billStatus && !item.paymentStatus" style="float: right; padding: 3px 0" type="text" @click="goPay(item.studentId, item.baseCheckInId)"> 付款 </el-button>
                <el-button v-show="!item.billStatus" style="float: right; padding: 3px 0" type="text" @click="showStudentStatistic(item.studentId, item.studentName)"> 结账 </el-button>
              </div>
              <div class="text item">
                <div>出勤天数：{{ item.checkInTimes }}<span style="float: right; padding: 3px 0">账单：<el-tag v-show="!item.billStatus" type="info">未生成</el-tag><el-tag v-show="item.billStatus">已生成</el-tag></span></div>
                <br/>
                <div>请假天数：{{ item.leaveDays }}<span style="float: right; padding: 3px 0">费用：<el-tag v-show="!item.paymentStatus" type="info">未支付</el-tag><el-tag type="success" v-show="item.paymentStatus">已结清</el-tag></span></div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-dialog
      :title="studentDetailTitle" :visible.sync="studentDetailVisible" width="80%" @close="resetForm" :closeOnClickModal="false" append-to-body>
      <div>
        <el-descriptions class="margin-top" title="考勤数据" direction="vertical" :column="4" border>
          <el-descriptions-item label="学生姓名">{{ studentCheckInDetail.studentName }}</el-descriptions-item>
          <el-descriptions-item label="考勤开始日期">{{ studentCheckInDetail.checkInBeginDate  }}</el-descriptions-item>
          <el-descriptions-item label="考勤结束日期">{{ studentCheckInDetail.checkInEndDate }}</el-descriptions-item>
          <el-descriptions-item label="缺勤天数">{{ studentCheckInDetail.checkInSumDays - studentCheckInDetail.checkInTimes - studentCheckInDetail.leaveDays }}</el-descriptions-item>
          <el-descriptions-item label="应考勤天数">{{ studentCheckInDetail.checkInSumDays }}</el-descriptions-item>
          <el-descriptions-item label="实际出勤天数">
            {{ studentCheckInDetail.checkInTimes }}
            <el-tag type="success" v-show="studentCheckInDetail.checkInSumDays === studentCheckInDetail.checkInTimes">全勤</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="请假天数">{{ studentCheckInDetail.leaveDays }}</el-descriptions-item>
        </el-descriptions>
        <el-divider></el-divider>
        <div>
          <el-row>
            <el-col :span="12">
              出勤详情：
              <el-row>
                <el-col :span="4" v-for="i in studentCheckInDaysDetail" style="margin-top:2px">
                  <el-tag>{{ i.checkInDate }}</el-tag>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="12">
              账 单 选 择：
              <el-select v-model="costTemplateId" placeholder="请选择" @change="showCostTemplateDetail">
                <el-option
                  v-for="item in costTemplateList"
                  :label="item.costTemplateName"
                  :value="item.costTemplateId">
                </el-option>
              </el-select>
              <div v-if="costTemplate">
                  <el-row style="margin-top: 5px">
                    <el-col :span="14">
                      周 期 收 费：<el-input style="width: 60%" disabled v-model="costTemplate.costFeePerMonth" @input="costTemplate.costFeePerMonth = costTemplate.costFeePerMonth.replace(/[^\d.]/g,'')" @blur="verifyFeeUse(0)"><template slot="append">元</template></el-input>
                      <i class="el-icon-circle-check" v-show="costTemplate.costUseFeePerMonth"></i>
                    </el-col>
                    <el-col :span="6">
                      <el-radio-group v-model="costTemplate.costUseFeePerMonth" size="small" @change="verifyFeeUse(0)">
                        <el-radio :label="1">使用</el-radio>
                      </el-radio-group>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 5px">
                    <el-col :span="14">
                      每 日 收 费：<el-input style="width: 60%" disabled v-model="costTemplate.costFeePerDay" @input="costTemplate.costFeePerDay = costTemplate.costFeePerDay.replace(/[^\d.]/g,'')" @blur="verifyFeeUse(1)"><template slot="append">元</template></el-input>
                      <i class="el-icon-circle-check" v-show="costTemplate.costUseFeePerDay"></i>
                    </el-col>
                    <el-col :span="6">
                      <el-radio-group v-model="costTemplate.costUseFeePerDay" size="small" @change="verifyFeeUse(1)">
                        <el-radio :label="1">使用</el-radio>
                      </el-radio-group>
                    </el-col>
                  </el-row>
                  <!-- el-row style="margin-top: 5px">
                    <el-col :span="14">
                      课 时 收 费：<el-input style="width: 60%" :disabled="!costTemplate.costFeePerLesson" v-model="costTemplate.costFeePerLesson" @input="costTemplate.costFeePerLesson = costTemplate.costFeePerLesson.replace(/[^\d.]/g,'')"><template slot="append">元</template></el-input>
                    </el-col>
                    <el-col :span="6">
                      <el-radio-group v-model="costTemplate.costUseFeePerLesson" size="small">
                        <el-radio :label="1">使用</el-radio>
                        <el-radio :label="0">不使用</el-radio>
                      </el-radio-group>
                    </el-col>
                  </el-row -->
                <el-row style="margin-top: 5px">
                  <el-col :span="14">
                    伙 食 收 费：
                    <el-input style="width: 60%" disabled v-model="costTemplate.costFoodFee" @input="costTemplate.costFoodFee = costTemplate.costFoodFee.replace(/[^\d.]/g,'')" placeholder="0"><template slot="append">元</template></el-input>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 5px">
                  <el-col :span="14">
                    资 料 收 费：
                    <el-input style="width: 60%" disabled v-model="costTemplate.costTextbooksFee" @input="costTemplate.costTextbooksFee = costTemplate.costTextbooksFee.replace(/[^\d.]/g,'')" placeholder="0"><template slot="append">元</template></el-input>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 5px">
                  <el-col :span="14">
                    预 收 费：
                    <el-input style="width: 60%" disabled v-model="advanceFee" @input="advanceFee = advanceFee.replace(/[^\d.]/g,'')" placeholder="0"><template slot="append">元</template></el-input>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 5px">
                  <el-col :span="14">
                    优 惠 费 用：
                    <el-input style="width: 60%" v-model="coupon" @input="coupon = coupon.replace(/[^\d.]/g,'')" placeholder="请输入优惠费用" @blur="calculateSumBill"><template slot="append">元</template></el-input>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 15px">
                  <el-col :span="4">
                    总费用：<br/><br/><br/><br/>合计：
                  </el-col>
                  <el-col :span="14">
                    <span>
                      <span v-if="costTemplate.costUseFeePerDay">
                        {{ studentCheckInDetail.checkInTimes }} × {{ costTemplate.costFeePerDay }} = {{ studentCheckInDetail.checkInTimes * costTemplate.costFeePerDay }}
                      </span>
                      <span v-else>
                        {{ costTemplate.costFeePerMonth }}
                      </span>
                      <br/>
                      + {{ costTemplate.costFoodFee }} 伙食费
                      <br/>
                      + {{ costTemplate.costTextbooksFee }} 资料费
                      <br/>
                      - {{ advanceFee === null ? 0:advanceFee }} 预收费
                      <br/>
                      - {{ coupon }} 优惠费用
                      <br/>
                      --------------------------------------------
                      <br/>
                      {{ sumCost }}元
                    </span>
                  </el-col>
                </el-row>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-form :model="formData" :rules="rules" size="medium" label-width="100px">
          <el-form-item size="large">
            <el-button @click="resetForm">取 消 结账</el-button>
            <el-button :disabled="costTemplate === null" type="primary" @click="submitForm">结账 {{ sumCost }} 元</el-button>
          </el-form-item>
        </el-form>
      </span>
    </el-dialog>
    <el-dialog
      title="支付" :visible.sync="payVisible" width="40%" :closeOnClickModal="false" append-to-body>
      <div>
        <h1>已生成账单，请选择付款方式</h1>
        <h1>付款金额：{{ sumCost }}</h1>
        <h2>付款方式</h2>
        <el-radio-group v-model="billMode">
          <el-radio :label="0">微信</el-radio>
          <el-radio :label="1">支付宝</el-radio>
          <el-radio :label="2">现金</el-radio>
          <el-radio :label="3">其他</el-radio>
        </el-radio-group>
        <br/><br/>
        <el-button type="primary" @click="resetForm2">暂不付款</el-button>
        <el-button type="primary" @click="payMoney">付款</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .box-card {
    margin: 5px;
  }
</style>
<script>
  import { listBaseCheckin, listStudents, statistic, studentStatistic, studentCheckInDays, getCostTemplate, getCostTemplateDetail, addStudentBill, updateStudentBill, listStudentBill, listAdvanceFee } from "@/api/payment/checkout";
  import { getGrades } from "@/api/info/studentInfo";

  export default {
    components: {},
    props: [],
    data() {
      return {
        billMode: null,
        payVisible: false,
        studentsLoading: false,
        // 总条数
        total: 0,
        // 考勤总表表格数据
        baseCheckinList: [],
        formData: {},
        rules: {},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        grades:[],
        grade: null,
        baseCheckInName: null,
        baseCheckInId: null,
        students: [],
        studentCheckInDetail: '',
        studentDetailVisible: false,
        studentDetailTitle: '',
        studentCheckInDaysDetail: [],
        costTemplateList: [],
        costTemplateId: null,
        costTemplate: null,
        coupon: 0,
        sumCost: 0,
        billId: null,
        advanceFee: null,
      }
    },
    computed: {},
    watch: {
      baseCheckInId : {
        handler(){
          this.showDetailDetail();
        }
      }
    },
    created() {
      this.initGrades();
      this.getList();
      this.showCostTemplateList();
    },
    mounted() {},
    methods: {
      calculateSumBill(){
        if(null !== this.costTemplate){
          let otherCost = this.costTemplate.costFoodFee + this.costTemplate.costTextbooksFee - this.coupon;
          if(1===this.costTemplate.costUseFeePerDay){
            this.sumCost = this.studentCheckInDetail.checkInTimes * this.costTemplate.costFeePerDay + otherCost;
          } else {
            this.sumCost = this.costTemplate.costFeePerMonth + otherCost;
          }
          if (this.advanceFee){
            this.sumCost = this.sumCost - this.advanceFee;
            if (this.sumCost < 0){
              this.sumCost = 0;
            }
          }
        }
      },
      verifyFeeUse(index){
        if (1 === index){
          null !== this.costTemplate.costFeePerDay && '' !== this.costTemplate.costFeePerDay ? this.changeUseAble(1,0) : this.changeUseAble(0,0);
        } else{
          null !== this.costTemplate.costFeePerMonth && '' !== this.costTemplate.costFeePerMonth ? this.changeUseAble(0,1) : this.changeUseAble(0,0);
        }
      },
      changeUseAble(useDay,useMonth){
        this.costTemplate.costUseFeePerDay = useDay;
        this.costTemplate.costUseFeePerMonth = useMonth;
        this.calculateSumBill();
      },
      showCostTemplateDetail(){
        getCostTemplateDetail(this.costTemplateId).then(response =>{
          this.costTemplate = response.data;
          this.calculateSumBill();
        });
      },
      showCostTemplateList(){
        getCostTemplate().then(response => {
          this.costTemplateList = response.data;
        });
      },
      showStudentCheckInDays(studentId){
        let param = {
          baseCheckInId : this.baseCheckInId,
          studentId : studentId,
          checkInStatus : 1
        };
        studentCheckInDays(param).then(response => {
          this.studentCheckInDaysDetail = response.data;
        });
      },
      showStudentStatistic(studentId, studentName){
        let param = {
          baseCheckInId : this.baseCheckInId,
          studentId : studentId
        };
        this.costTemplateList.forEach(item => {
          if (item.costUseGrade === this.grade){
            this.costTemplateId = item.costTemplateId;
            this.showCostTemplateDetail();
          }
        });
        studentStatistic(param).then(response => {
          this.studentCheckInDetail = response.data[0];
          this.studentDetailTitle = this.baseCheckInName + "  " + studentName + "  考勤详情";
          this.studentDetailVisible = true;
        });
        listAdvanceFee(param).then(response => {
          if (response.total > 0){
            this.advanceFee = response.rows[0].advanceFee;
          }
        });
        this.showStudentCheckInDays(studentId);
      },
      calculateStatistic(baseCheckInId,baseCheckInName){
        let param = {
          baseCheckInId : baseCheckInId
        }
        this.baseCheckInId = baseCheckInId;
        this.baseCheckInName = baseCheckInName;
        statistic(param).then(response => {
          this.$modal.msgSuccess(baseCheckInName + " 考勤数据统计成功");
          this.showDetailDetail();
        });
      },
      initGrades(){
        getGrades().then(response => {
          this.grades = response.data;
          this.grade = this.grades[0].grade;
        })
      },
      showDetail(baseCheckInId,baseCheckInName){
        if (null === baseCheckInId){
          return
        }
        if (null === this.grade){
          return
        }
        if (baseCheckInName !== null){
          this.baseCheckInId = baseCheckInId;
          this.baseCheckInName = baseCheckInName;
        }
      },
      showDetailDetail(){
        this.studentsLoading = true;
        let param = {
          baseCheckInId : this.baseCheckInId,
          studentGrade : this.grade
        };
        listStudents(param).then(response => {
          this.students = response.data;
          this.studentsLoading = false;
        });
      },
      /** 查询考勤总表列表 */
      getList() {
        this.loading = true;
        let that = this;
        listBaseCheckin(this.queryParams).then(response => {
          that.baseCheckinList = response.rows;
          if (this.baseCheckinList.length > 0){
            this.baseCheckInId = this.baseCheckinList[0].baseCheckInId;
            this.baseCheckInName = this.baseCheckinList[0].baseCheckInName;
          }
          that.total = response.total;
          that.loading = false;
        });
      },
      submitForm() {
        let param = {
          costTemplateId: this.costTemplateId,
          studentId: this.studentCheckInDetail.studentId,
          studentName: this.studentCheckInDetail.studentName,
          checkInStatisticId: this.studentCheckInDetail.id,
          coupon: this.coupon,
          advanceFee: this.advanceFee,
          costUseFeePerMonth: this.costTemplate.costUseFeePerMonth,
          actualPerMonthFee: this.costTemplate.costFeePerMonth,
          actualPerDayFee: this.costTemplate.costFeePerDay,
          actualTextbookFee: this.costTemplate.costTextbooksFee,
          actualFoodFee: this.costTemplate.costFoodFee,
          acutalBillFee: this.sumCost,
          billStatus: 0,
        }
        if(this.costTemplate.costUseFeePerDay === 1){
          param.actualPerMonthFee = null;
        } else {
          param.actualPerDayFee = null;
        }
        addStudentBill(param).then(response => {
          this.payVisible = true;
          this.billId = response.data;
        });
      },
      goPay(studentId,baseCheckInId){
        let param = {
          studentId: studentId,
          checkInBaseId: baseCheckInId
        }
        listStudentBill(param).then(response => {
          this.sumCost = response.data[0].acutalBillFee;
          this.billId = response.data[0].id;
          this.payVisible = true;
        });
      },
      payMoney(){
        let param = {
          billMode: this.billMode,
          id: this.billId,
          billStatus: 1,
        }
        updateStudentBill(param).then(() => {
          this.$message.success("缴费成功");
          this.payVisible = false;
          this.showDetailDetail();
          this.getList();
          this.resetForm();
        });
      },
      resetForm2() {
        this.payVisible = false;
        this.showDetailDetail();
        this.getList();
        this.resetForm();
      },
      resetForm() {
        this.studentDetailVisible = false;
        this.costTemplate = null;
        this.studentCheckInDetail = '';
        this.costTemplateId = null;
        this.sumCost = 0;
        this.billId = null;
        this.advanceFee = null;
        this.payVisible = false;
      },
    }
  }

</script>
<style>
</style>
