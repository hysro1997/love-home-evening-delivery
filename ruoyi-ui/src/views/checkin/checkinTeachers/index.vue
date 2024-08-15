<template>
  <div class="app-container">
    <el-form ref="elForm" size="medium" label-width="100px"
             label-position="top">
      <el-row :gutter="10">
        <el-col>
          <el-form-item :label="checkinTitle">
            <el-table
              :data="teachers"
              style="width: 100%">
              <el-table-column
                prop="teacherName"
                label="姓名">
              </el-table-column>
              <el-table-column
                prop="checkInStatus"
                label="签到">
                <template slot-scope="scope">
                  <el-radio-group v-model="scope.row.checkInStatus" @input="alterCheckinTeacher(scope.row)">
                    <el-radio :label="0" border style="margin: 2px">未到</el-radio>
                    <el-radio :label="1" border style="margin: 2px">签到</el-radio>
                    <el-radio :label="2" border style="margin: 2px">迟到</el-radio>
                    <el-radio :label="3" border style="margin: 2px">请假</el-radio>
                    <el-radio :label="null" border style="margin: 2px" disabled>默认</el-radio>
                  </el-radio-group>
                </template>
              </el-table-column>
              <el-table-column
                prop="checkInDayOnDutyTimes"
                label="当日值班次数">
                <template slot-scope="scope">
                  <el-radio-group v-model="scope.row.checkInDayOnDutyTimes" @input="alterCheckinTeacher(scope.row)">
                    <el-radio :label="0" border style="margin: 2px">无</el-radio>
                    <el-radio :label="1" border style="margin: 2px">1次</el-radio>
                    <el-radio :label="2" border style="margin: 2px">2次</el-radio>
                    <el-radio :label="3" border style="margin: 2px">3次</el-radio>
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
  import { listTeacherCheckin, updateTeacherCheckin } from "@/api/checkin/checkinTeachers";
  export default {
    name: "CheckinTeachers",
    components: {},
    props: [],
    data() {
      return {
        teachers: [],
        checkinTitle: null
      }
    },
    computed: {},
    watch: {},
    created() {
      this.getCheckinTeachersList();
      this.setTitle();
    },
    mounted() {},
    methods: {
      alterCheckinTeacher(row){
        updateTeacherCheckin(row).then(() => {
          this.$modal.msgSuccess("成功修改" + row.teacherName + "考勤状态！");
          this.getCheckinTeachersList();
        });
      },
      setTitle(){
        let today = new Date();
        this.checkinTitle = (today.getMonth() + 1) + " 月 " + today.getDate() + " 日 教师考勤"
      },
      getCheckinTeachersList(){
        let params = {
          checkInDate: (new Date()).toLocaleDateString()
        };
        listTeacherCheckin(params).then(response =>{
          this.teachers = response.rows;
        });
      }
    }
  }

</script>
<style>
</style>
