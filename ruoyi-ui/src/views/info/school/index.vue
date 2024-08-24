<template>
  <div class="app-container">
    <el-row>
      <el-col :span="12">
        <el-row>
          <el-form ref="studentFormIndex" :model="form.studentInfo" :rules="rules" label-width="80px">
            <el-row :gutter="5">
              <el-col :span="14">
                <el-form-item label="学生姓名" prop="studentName">
                <el-input ref="studentNameRef" v-model="form.studentInfo.studentName" placeholder="学生姓名（如果重复，请添加后缀）" />
              </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="学生性别" prop="studentGender">
                  <template>
                    <el-radio-group v-model="form.studentInfo.studentGender">
                      <el-radio :label="1">男</el-radio>
                      <el-radio :label="2">女</el-radio>
                    </el-radio-group>
                  </template>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="5">
              <el-col :span="8">
                <el-form-item label="学生学校" prop="studentSchool">
                  <el-select v-model="form.studentInfo.studentSchool" placeholder="请选择学校">
                    <el-option
                      v-for="item in schools"
                      :key="item.id"
                      :label="item.schoolName"
                      :value="item.schoolName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="学生年级" prop="studentGrade">
                  <el-select v-model="form.studentInfo.studentGrade" placeholder="请选择年级">
                    <el-option
                      v-for="item in grades"
                      :key="item.id"
                      :label="item.grade"
                      :value="item.grade">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="学生班级" prop="studentClass">
                  <el-select v-model="form.studentInfo.studentClass" placeholder="请选择班级">
                    <el-option
                      v-for="item in studentClasses"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="5">
              <el-col :span="12">
                <el-form-item label="联系电话" prop="studentPhone">
                  <el-input v-model="form.studentInfo.studentPhone" placeholder="请输入联系电话" maxlength="11"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="学生来源" prop="studentSource">
                  <el-select v-model="form.studentInfo.studentSource" placeholder="请选择学生来源">
                    <el-option
                      v-for="item in studentSource"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="5">
              <el-col :span="10">
                <el-form-item label="考勤表" prop="baseCheckInId">
                  <el-select v-model="form.baseCheckIn.baseCheckInId" placeholder="请选择考勤"
                             filterable :remote-method="remoteQueryBaseCheckInList"
                             remote>
                    <el-option
                      v-for="item in baseCheckinList"
                      :key="item.baseCheckInId"
                      :label="item.baseCheckInName"
                      :value="item.baseCheckInId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="14">
                <el-form-item label="备注信息" prop="studentRemark">
                  <el-input v-model="form.studentInfo.studentRemark" type="textarea" :rows="2" placeholder="请输入备注信息" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div style="text-align: right">
            <el-button type="primary" @click="submitForm">添加学生</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-row>
      </el-col>
      <el-col :span="12"></el-col>
    </el-row>

  </div>
</template>

<script>
  import { listStudentInfo, getStudentInfo, delStudentInfo, addStudentInfo, updateStudentInfo, updateStudentStatus, getGrades, getSchools } from "@/api/info/studentInfo";
  import { listBaseCheckin } from "@/api/checkin/baseCheckin";
  import { addHomoInBaseCheckin } from "@/api/checkin/homoInBaseCheckin";


  export default {
    name: 'index',
    data(){
      return {
        // 表单参数
        form: {
          studentInfo: {},
          baseCheckIn: {}
        },
        // 表单校验
        rules: {
          studentName: [
            { required: true, message: "学生姓名不能为空", trigger: "blur" }
          ],
          studentGrade: [
            { required: true, message: "学生年级不能为空", trigger: "blur" }
          ],
          studentPhone: [
            { required: true, message: "联系电话不能为空", trigger: "blur" },
            {
              pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
              message: "请输入正确的手机号码",
              trigger: "blur"
            }
          ],
        },
        schools:[],
        grades:[],
        studentClasses: [{label:"1班",value:"1班"},{label:"2班",value:"2班"},{label:"3班",value:"3班"},{label:"4班",value:"4班"},{label:"5班",value:"5班"},{label:"6班",value:"6班"},{label:"7班",value:"7班"},{label:"8班",value:"8,班"},{label:"9班",value:"9班"},{label:"10班",value:"10班"}],
        studentSource: [{label:"自然",value:"自然"},{label:"宣传",value:"宣传"},{label:"学员推荐",value:"学员推荐"},{label:"其他",value:"其他"}],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 20,
          baseCheckInName: null,
        },
        baseCheckinList: []
      }
    },
    created() {
      this.initGrades();
      this.initSchools();
      this.getBaseCheckinList();
    },
    methods: {
      initGrades(){
        getGrades().then(response => {
          this.grades = response.data;
        })
      },
      initSchools(){
        getSchools().then(response => {
          this.schools = response.data;
        })
      },
      // 取消按钮
      cancel() {
        this.reset();
      },
      // 表单重置
      reset() {
        this.form.studentInfo = {
          studentId: null,
          studentName: null,
          studentGender: null,
          studentSchool: null,
          studentGrade: null,
          studentClass: null,
          studentPhone: null,
          studentFace: null,
          studentSource: null,
          studentRemark: null,
          studentStatus: null
        };
        this.resetForm("studentFormIndex");
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["studentFormIndex"].validate(valid => {
          if (valid) {
            this.form.studentInfo.studentName = this.form.studentInfo.studentName.trim();
            addStudentInfo(this.form.studentInfo).then(response => {
              if (response.data !== 0){
                let param = {
                  baseCheckInId: this.form.baseCheckIn.baseCheckInId,
                  studentId: response.data,
                  studentName: this.form.studentInfo.studentName
                }
                addHomoInBaseCheckin(param).then(response => {
                  this.$modal.msgSuccess("新增成功");
                });
                this.reset();
              } else {
                this.$refs['studentNameRef'].focus();
                this.$modal.msgWarning("学生姓名重复");
              }
            });
          }
        });
      },
      /** 查询考勤总表列表 */
      getBaseCheckinList() {
        let that = this;
        listBaseCheckin(this.queryParams).then(response => {
          that.baseCheckinList = response.rows;
          if (that.baseCheckinList.length > 0){
            that.form.baseCheckIn.baseCheckInId = that.baseCheckinList[0].baseCheckInId;
          }
        });
      },
      remoteQueryBaseCheckInList(query){
        let param = {
          baseCheckInName: query
        };
        let that = this;
        listBaseCheckin(param).then(response => {
          that.baseCheckinList = response.rows;
        });
      },
    }
  }
</script>

<style scoped>

</style>
