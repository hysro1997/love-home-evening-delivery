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
              账单状态：<el-tag v-show="i.billStatus === 0" type="info">未生成</el-tag><el-tag v-show="i.billStatus === 1" type="warning">部分账单</el-tag><el-tag v-show="i.billStatus === 2">已生成</el-tag>
            </div>
            <div>
              支付状态：<el-tag v-show="i.paymentStatus === 0" type="info">未支付</el-tag><el-tag v-show="i.paymentStatus === 1" type="warning">部分支付</el-tag><el-tag v-show="i.paymentStatus === 2">已结清</el-tag>
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
                <el-button style="float: right; padding: 3px 0" type="text">结账</el-button>
              </div>
              <div class="text item">
                <div>出勤天数：{{ item.checkInTimes }}<span style="float: right; padding: 3px 0"><el-tag v-show="item.billStatus === 0 || !item.billStatus" type="info">未生成</el-tag><el-tag v-show="item.billStatus === 1">已生成</el-tag></span></div>
                <br/>
                <div>请假天数：{{ item.leaveDays }}<span style="float: right; padding: 3px 0"><el-tag v-show="item.paymentStatus === 0 || !item.paymentStatus" type="info">未支付</el-tag><el-tag v-show="item.paymentStatus === 1">已结清</el-tag></span></div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" :disabled="true" label-width="100px">
      <el-row :gutter="15">
      </el-row>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
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
  import { listBaseCheckin, listStudents, statistic } from "@/api/payment/checkout";
  import { getGrades } from "@/api/info/studentInfo";

  export default {
    components: {},
    props: [],
    data() {
      return {
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
    },
    mounted() {},
    methods: {
      calculateStatistic(baseCheckInId,baseCheckInName){
        let param = {
          baseCheckInId : baseCheckInId
        }
        statistic(param).then(response => {
          this.$modal.msgSuccess(baseCheckInName + " 考勤数据统计成功");
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
          }
          that.total = response.total;
          that.loading = false;
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
    }
  }

</script>
<style>
</style>
