<template>
  <div class="app-container">
    <el-form ref="elForm" size="medium" label-width="100px"
             label-position="top">
      <el-row :gutter="15">
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary" size="medium" @click="getCheckinStudentsList(null)"> 全体 </el-button>
          </el-form-item>
        </el-col>
        <el-col :span="6" v-for="grade in gradeList">
          <el-form-item>
            <el-button type="primary" size="medium" @click="getCheckinStudentsList(grade.grade)"> {{grade.grade}} </el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>将当前 {{checkinTitle}} 考勤名单 全部设为：<el-button @click="alterAllCheckinStudent(0)">未到</el-button><el-button @click="alterAllCheckinStudent(1)">签到</el-button><el-button @click="alterAllCheckinStudent(2)">请假</el-button></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col>
          <el-form-item :label="checkinTitle">
            <el-table
              :data="students"
              style="width: 100%">
              <el-table-column
                prop="checkInStudentName"
                label="姓名"
                width="180">
              </el-table-column>
              <el-table-column
                prop="checkInStudentGrade"
                label="年级"
                width="180">
              </el-table-column>
              <el-table-column
                prop="checkInStatus"
                label="签到">
                <template slot-scope="scope">
                  <el-radio-group v-model="scope.row.checkInStatus" @input="alterCheckinStudent(scope.row)">
                    <el-radio :label="0" border style="margin: 2px">未到</el-radio>
                    <el-radio :label="1" border style="margin: 2px">签到</el-radio>
                    <el-radio :label="2" border style="margin: 2px">请假</el-radio>
                    <el-radio :label="null" border style="margin: 2px" disabled>默认</el-radio>
                  </el-radio-group>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
  import { listGrade, listCheckinStudents, updateCheckinStudent, updateCheckinStudentList } from "@/api/checkin/checkinStudents";
  export default {
    name: "CheckinStudents",
    components: {},
    props: [],
    data() {
      return {
        // 年级表格数据
        gradeList: [],
        students: [],
        checkinTitle: '',
        grade: null
      }
    },
    computed: {},
    watch: {},
    created() {
      this.getGradeList();
      this.getCheckinStudentsList(null);
    },
    mounted() {},
    methods: {
      alterAllCheckinStudent(status){
        this.students.forEach(function(e) {
          e.checkInStatus = status;
        });
        updateCheckinStudentList(this.students).then(() => {
          switch (status) {
            case 0:
              this.$modal.msgSuccess(this.checkinTitle + " 今日全部未到!");
              break;
            case 1:
              this.$modal.msgSuccess(this.checkinTitle + " 今日全部签到成功!");
              break;
            case 2:
              this.$modal.msgSuccess(this.checkinTitle + " 今日全部请假成功!");
              break
            default:
              this.$modal.msgSuccess("意外!");
          }
        });
      },
      alterCheckinStudent(row){
        updateCheckinStudent(row).then(() => {
          switch (row.checkInStatus) {
            case 0:
              this.$modal.msgSuccess(row.checkInStudentName + " 今日未到!");
              break;
            case 1:
              this.$modal.msgSuccess(row.checkInStudentName + " 今日签到成功!");
              break;
            case 2:
              this.$modal.msgSuccess(row.checkInStudentName + " 请假成功!");
              break
            default:
              this.$modal.msgSuccess("意外!");
          }
          this.getCheckinStudentsList(this.grade);
        });
      },
      getCheckinStudentsList(grade){
        this.checkinTitle = (grade === null ? "全体" : grade) + "签到情况";
        this.grade = grade;
        let parms = {
          checkInStudentGrade: grade,
          checkInDate: (new Date()).toLocaleDateString()
        };
        listCheckinStudents(parms).then(response => {
          this.students = response.rows;
        });
      },
      getGradeList() {
        listGrade().then(response => {
          this.gradeList = response.rows;
        });
      }
    }
  }

</script>
<style>
</style>
