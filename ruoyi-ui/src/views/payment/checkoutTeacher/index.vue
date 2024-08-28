<template>
  <div>
    <el-row>
      <el-col :span="8" style="margin-left:15px">
        <div style="text-align: left" v-for="i in teacherList" :key="i.teacherId">
          <el-card class="box-card" shadow="always" style="margin: 6px">
            <div slot="header" class="clearfix">
              <span>{{ i.teacherName }}</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click.native="showTeacherSalary(i.teacherId)">查看详情</el-button>
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
            <span class="demonstration">日期范围：</span>
            <el-date-picker
              v-model="rangeDate"
              type="daterange"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
            <el-button type="primary" round @click.native="calculateTeacherCheckin">统计考勤</el-button>
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
  import { listTeachers, listTeacherSalary } from "@/api/payment/checkoutTeacher";
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
      }
    },
    computed: {},
    watch: {},
    created() {
      this.initTeachers();
    },
    mounted() {},
    methods: {
      calculateTeacherCheckin(){
        if ('' === this.rangeDate){
          this.$message.warning("没有选择统计日期范围");
          return;
        } else {
          
        }
      },
      showTeacherSalary(teacherId){
        this.queryParams2.teacherId = teacherId;
        listTeacherSalary(this.queryParams2).then(response => {
          this.teacherSalaryList = response.rows;
          this.teacherSalaryTotal = response.total;
        });
      },
      initTeachers(){
        listTeachers(this.queryParams).then(response => {
          this.teacherList = response.rows;
          this.teacherTotal = response.total;
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
