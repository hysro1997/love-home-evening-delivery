<template>
  <div class="app-container">
    <el-row>
      <el-col :span="8" style="text-align: center">考勤总表<br/>
        <div style="text-align: center">
          <el-row :gutter="10">
            <el-col :span="16">考勤表名称</el-col>
            <el-col :span="4">账单</el-col>
            <el-col :span="4">支付</el-col>
          </el-row>
        </div>
        <div style="text-align: left">
            <div v-for="i in baseCheckinList" :key="i.baseCheckInId">
              <el-row :gutter="10" class="checkin-class" style="margin-top: 20px;margin-bottom: 20px;border-bottom:solid 1px;" @click.native="showDetail(i.baseCheckInId)">
                <el-col :span="16" style="padding-left: 20px">{{ i.baseCheckInName }}</el-col>
                <el-col :span="4">
                  <el-tag v-show="i.billStatus === 0" type="info">未生成</el-tag><el-tag v-show="i.billStatus === 1" type="warning">部分账单</el-tag><el-tag v-show="i.billStatus === 2">已生成</el-tag>
                </el-col>
                <el-col :span="4">
                  <el-tag v-show="i.paymentStatus === 0" type="info">未支付</el-tag><el-tag v-show="i.paymentStatus === 1" type="warning">部分支付</el-tag><el-tag v-show="i.paymentStatus === 2">已结清</el-tag>
                </el-col>
              </el-row>
            </div>
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
      <el-col :span="14" style="margin-left: 15px;">选择年级
        <el-row>
          <el-radio-group v-model="grade" v-for="i in grades" @input="showDetail()">
            <el-radio-button style="margin: 10px" :label="i.grade"></el-radio-button>
          </el-radio-group>
        </el-row>
        查看明细
        <el-row>
          <el-col :span="8" v-for="item in students">
            <el-card class="box-card" style="width: 250px;height: 150px">
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
  .checkin-class:hover {
    background: #b6f9f4;
  }
  .checkin-class:active {
    background: #2c94ff;
  }
</style>
<script>
  import { listBaseCheckin, listStudents } from "@/api/payment/checkout";
  import { getGrades } from "@/api/info/studentInfo";

  export default {
    components: {},
    props: [],
    data() {
      return {
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
        baseCheckInId: null,
        students: [],
      }
    },
    computed: {},
    watch: {},
    created() {
      this.getList();
      this.initGrades();
    },
    mounted() {},
    methods: {
      initGrades(){
        getGrades().then(response => {
          this.grades = response.data;
          this.grade = this.grades[0].grade;
        })
      },
      initBaseCheckInId(baseCheckInId){
        this.baseCheckInId = baseCheckInId;
        this.showDetail();
      },
      showDetail(){
        if (null === this.baseCheckInId){
          return
        }
        if (null === this.grade){
          return
        }
        let param = {
          baseCheckInId : this.baseCheckInId,
          studentGrade : this.grade
        };
        listStudents(param).then(response => {
          this.students = response.data;
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
