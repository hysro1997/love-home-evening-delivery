<template>
  <div class="app-container" v-loading="loadingStatus">
    <el-row>
      <el-col :span="14" style="border-right: solid">
        <el-row style="margin-right: 15px">
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
                             filterable :remote-method="remoteQueryBaseCheckInList" @change="$forceUpdate()"
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
        <el-row style="border-top: solid;margin-top: 10px">
          <div style="margin-top: 10px">
            <el-col :span="8" v-for="i in studentInfoList" :key="i.studentId">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>{{i.studentName}}</span>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="showStudentInfo(i.studentId)">编辑</el-button>
                </div>
                <div  class="text item">
                  {{i.studentGrade}}
                </div>
                <div  class="text item">
                  {{i.studentPhone}}
                </div>
              </el-card>
            </el-col>
          </div>
        </el-row>
        <el-row>
          <pagination
            v-show="studentTotal>0"
            :total="studentTotal"
            :page.sync="queryParams3.pageNum"
            :limit.sync="queryParams3.pageSize"
            @pagination="listStudentList"
          />
        </el-row>
        <el-row style="margin-top: 20px;">
          <div style="margin-left:10px;text-align:center;">
            查看考勤表下的学生：<el-select v-model="queryParams4.baseCheckInId" placeholder="请选择考勤"
                                 filterable :remote-method="remoteQueryBaseCheckInList4" @change="$forceUpdate()"
                                 remote>
            <el-option
              v-for="item in baseCheckinList4"
              :key="item.baseCheckInId"
              :label="item.baseCheckInName"
              :value="item.baseCheckInId">
            </el-option>
          </el-select>
          </div>
          <div style="margin-top: 10px;margin-bottom: 10px">
              <el-transfer
                style="text-align: left; display: inline-block;"
                @change="changeStudentsInCheckIn"
                filterable
                v-model="checkinStudentsList"
                :titles="['学生列表', '考勤']"
                :button-texts="['取消考勤','进入考勤']"
                :data="studentsInSchool"
                :props="{key:'studentId',label:'studentName',disabled:false}">
                <span slot-scope="{ option }">{{ option.studentGrade }} - {{ option.studentName }}</span>
              </el-transfer>
          </div>
        </el-row>
      </el-col>
      <el-col :span="10">
        <div style="margin-left:10px;text-align:center;">
          查看考勤表下的学生：<el-select v-model="queryParams2.baseCheckInId" placeholder="请选择考勤"
                     filterable :remote-method="remoteQueryBaseCheckInList2" @change="$forceUpdate()"
                     remote>
            <el-option
              v-for="item in baseCheckinList2"
              :key="item.baseCheckInId"
              :label="item.baseCheckInName"
              :value="item.baseCheckInId">
            </el-option>
          </el-select>
          <el-button @click="resetSelect">重置</el-button>
          <el-input v-model="selectStudentName" placeholder="请输入查找的学生姓名" @blur="showStudentsInBaseCheckInHomo" @keyup.native.enter="showStudentsInBaseCheckInHomo" />
        </div>
        <ul style="margin-left:10px;">
          <li class="info-block" v-for="i in homoInBaseCheckIn">{{i.studentName}}
            <div class="blank-block"></div>
            <el-button round @click="handleAdvanceFee(i.studentId, i.studentName)">预付金</el-button>
            <el-button round @click="cancelCheckIn(i.id)">取消考勤</el-button>
          </li>
        </ul>
        <div>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams2.pageNum"
            :limit.sync="queryParams2.pageSize"
            @pagination="showStudentsInBaseCheckInHomo"
          />
        </div>
      </el-col>
    </el-row>
    <!-- 添加或修改预收费对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :closeOnClickModal="false" append-to-body>
      <el-form ref="advanceFeeForm2" :model="feeForm" :rules="rules" label-width="80px">
        <el-form-item label="预交费" prop="advanceFee">
          <el-input v-model="feeForm.advanceFee" placeholder="请输入预交费" />
        </el-form-item>
        <el-form-item label="备注" prop="memo">
          <el-input v-model="feeForm.memo" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="核实状态" prop="verifyAdvanceFee">
          <el-select v-model="feeForm.verifyAdvanceFee" placeholder="请选择核实状态">
            <el-option
              v-for="item in feeStatusOptions"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel2">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改学生信息对话框 -->
    <el-dialog title="修改学生信息" :visible.sync="studentOpen" width="1000px" :closeOnClickModal="false" append-to-body>
      <el-form ref="studentForm3" :model="studentForm" :rules="rules" label-width="120px">
        <el-row :gutter="20">
          <el-col :span="10"><el-form-item label="学生姓名" prop="studentName">
            <el-input v-model="studentForm.studentName" placeholder="请输入学生姓名（如果重复，请添加后缀）" />
          </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="学生性别" prop="studentGender">
              <template>
                <el-radio-group v-model="studentForm.studentGender">
                  <el-radio :label="1">男</el-radio>
                  <el-radio :label="2">女</el-radio>
                </el-radio-group>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="学生学校" prop="studentSchool">
              <el-select v-model="studentForm.studentSchool" placeholder="请选择学校">
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
              <el-select v-model="studentForm.studentGrade" placeholder="请选择年级">
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
              <el-select v-model="studentForm.studentClass" placeholder="请选择班级">
                <el-option
                  v-for="item in studentClasses"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系电话" prop="studentPhone">
              <el-input v-model="studentForm.studentPhone" placeholder="请输入联系电话" maxlength="11"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学生来源" prop="studentSource">
              <el-select v-model="studentForm.studentSource" placeholder="请选择学生来源">
                <el-option
                  v-for="item in studentSource"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注信息" prop="studentRemark">
          <el-input v-model="studentForm.studentRemark" type="textarea" :rows="2" placeholder="请输入备注信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitStudentForm">确 定</el-button>
        <el-button @click="cancel3">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import { listStudentInfo, addStudentInfo, updateStudentInfo, getStudentInfo, getGrades, getSchools,listBaseCheckin, addHomoInBaseCheckin, listHomoInBaseCheckin, delHomoInBaseCheckin, listAdvanceFee, addAdvanceFee, updateAdvanceFee, getInSchoolStudents, getBaseCheckin, updateHomoInCheckin } from "@/api/info/schoolManage";

  export default {
    name: 'index',
    data(){
      return {
        selectStudentName: null,
        loadingStatus: false,
        checkinStudentsList: [],
        studentsInSchool: [],
        studentOpen: false,
        studentForm: {},
        title: '预收费',
        feeStatusOptions :[{label:"预交费", value: 0},{label:"已核销", value: 1},{label:"已退款作废", value: 2}],
        open: false,
        total: 0,
        queryParams2: {
          pageNum: 1,
          pageSize: 20,
          baseCheckInId: null,
        },
        queryParams4: {
          pageNum: 1,
          pageSize: 20,
          baseCheckInId: null,
        },
        feeForm: {
          verifyAdvanceFee: 0
        },
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
        baseCheckinList: [],
        baseCheckinList2: [],
        baseCheckinList4: [],
        homoInBaseCheckIn: [],
        studentInfoList: [],
        studentTotal:0,
        queryParams3: {
          pageNum: 1,
          pageSize: 10,
          studentStatus: 0
        },
      }
    },
    created() {
      this.initGrades();
      this.initSchools();
      this.getBaseCheckinList();
      this.listStudentList();
      this.getStudentsAndTeachers();
    },
    watch: {
      'queryParams2.baseCheckInId' : {
        handler(){
          if (this.queryParams2.baseCheckInId !== null){
            this.showStudentsInBaseCheckInHomo();
          }
        }
      },
      'queryParams4.baseCheckInId' : {
        handler(){
          if (this.queryParams4.baseCheckInId !== null){
            this.showBaseCheckInDetail();
          }
        }
      }
    },
    methods: {
      resetSelect(){
        this.form.baseCheckIn.baseCheckInId = null;
        this.queryParams2.baseCheckInId = null;
        this.queryParams4.baseCheckInId = null;
        this.selectStudentName = null;
      },
      changeStudentsInCheckIn(){
        this.loadingStatus = true;
        let param = {
          baseCheckInId: this.queryParams4.baseCheckInId,
          checkinStudents: this.checkinStudentsList
        }
        updateHomoInCheckin(param).then(response => {
          this.$modal.msgSuccess("修改考勤人员成功");
          this.showStudentsInBaseCheckInHomo();
          this.loadingStatus = false;
        });
      },
      showBaseCheckInDetail(){
        let that = this;
        getBaseCheckin(that.queryParams4.baseCheckInId).then(response => {
          let temp = response.data;
          temp.students = [];
          temp.checkinStudents = [];
          temp.ajHomoInBaseCheckIns.forEach(function(element) {
            if (null !== element.studentId){
              temp.students.push(element);
              temp.checkinStudents.push(element.studentId);
            }
          });
          that.checkinStudentsList = temp.checkinStudents;
        });
      },
      getStudentsAndTeachers(){
        getInSchoolStudents().then(response => {
          this.studentsInSchool = response.data;
        });
      },
      listStudentList(){
        listStudentInfo(this.queryParams3).then(response => {
          this.studentInfoList = response.rows;
          this.studentTotal = response.total;
        });
      },
      submitForm2(){
        this.loadingStatus = true;
        if (this.feeForm.id){
          updateAdvanceFee(this.feeForm).then(() => {
            this.$modal.msgSuccess("修改预付费成功");
            this.cancel2();
            this.loadingStatus = false;
          })
        } else {
          addAdvanceFee(this.feeForm).then(() => {
            this.$modal.msgSuccess("添加预付费成功");
            this.cancel2();
            this.loadingStatus = false;
          })
        }
      },
      handleAdvanceFee(studentId,studentName){
        this.reset2();
        let params = {
          studentId: studentId,
          studentName: studentName,
          baseCheckInId: this.queryParams2.baseCheckInId
        }
        listAdvanceFee(params).then(response => {
          if (response.rows.length > 0){
            this.feeForm = response.rows[0];
          } else {
            this.feeForm.studentId = studentId;
            this.feeForm.studentName = studentName;
            this.feeForm.baseCheckInId = this.queryParams2.baseCheckInId;
          }
        });
        this.title = studentName + ' 预付费';
        this.open = true;
      },
      cancelCheckIn(id){
        this.loadingStatus = true;
        delHomoInBaseCheckin(id).then(() => {
          this.$modal.msgSuccess("取消考勤成功");
          this.showStudentsInBaseCheckInHomo();
          this.showBaseCheckInDetail();
          this.loadingStatus = false;
        });
      },
      showStudentsInBaseCheckInHomo(){
        let param = {
          baseCheckInId: this.queryParams2.baseCheckInId,
          studentName: this.selectStudentName
        };
        listHomoInBaseCheckin(param).then(response => {
          this.homoInBaseCheckIn = response.rows;
          this.total = response.total;
          this.showBaseCheckInDetail();
        });
      },
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
      // 取消按钮
      cancel2() {
        this.open = false;
        this.reset2();
      },
      // 表单重置
      reset2() {
        this.feeForm = {
          id: null,
          studentId: null,
          studentName: null,
          baseCheckInId: null,
          advanceFee: null,
          memo: null,
          verifyAdvanceFee: 0
        };
        this.resetForm("advanceFeeForm2");
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
            this.loadingStatus = true;
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
                  this.loadingStatus = false;
                  this.showStudentsInBaseCheckInHomo();
                  this.listStudentList();
                  this.showBaseCheckInDetail();
                });
                this.reset();
              } else {
                this.loadingStatus = false;
                this.$refs['studentNameRef'].focus();
                this.$modal.msgWarning("学生姓名重复");
                this.form.studentInfo.studentName = this.form.studentInfo.studentName + this.form.studentInfo.studentPhone.slice(7,11);
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
          that.baseCheckinList2 = response.rows;
          that.baseCheckinList4 = response.rows;
          if (that.baseCheckinList.length > 0){
            that.form.baseCheckIn.baseCheckInId = that.baseCheckinList[0].baseCheckInId;
            that.queryParams2.baseCheckInId = that.baseCheckinList[0].baseCheckInId;
            that.queryParams4.baseCheckInId = that.baseCheckinList[0].baseCheckInId;
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
      remoteQueryBaseCheckInList2(query){
        let param = {
          baseCheckInName: query
        };
        let that = this;
        listBaseCheckin(param).then(response => {
          that.baseCheckinList2 = response.rows;
        });
      },
      remoteQueryBaseCheckInList4(query){
        let param = {
          baseCheckInName: query
        };
        let that = this;
        listBaseCheckin(param).then(response => {
          that.baseCheckinList4 = response.rows;
        });
      },
      /** 提交按钮 */
      submitStudentForm() {
        this.studentForm.studentName = this.studentForm.studentName.trim();
        this.$refs["studentForm3"].validate(valid => {
          if (valid) {
            this.loadingStatus = true;
              updateStudentInfo(this.studentForm).then(response => {
                if (response.data !== 0){
                  this.$modal.msgSuccess("修改成功");
                } else {
                  this.$modal.msgWarning("学生姓名重复");
                }
                this.studentOpen = false;
                this.loadingStatus = false;
              });
          }
        });
      },// 取消按钮
      cancel3() {
        this.studentOpen = false;
        this.reset3();
      },
      // 表单重置
      reset3() {
        this.studentForm = {
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
        this.resetForm("studentForm3");
      },
      showStudentInfo(studentId){
        this.reset3();
        getStudentInfo(studentId).then(response => {
          this.studentForm = response.data;
          this.studentOpen = true;
        });
      }
    }
  }
</script>

<style scoped>
  /deep/ .el-transfer-panel{
    height: 500px;
  }
  /deep/ .el-transfer-panel__list{
    height: 500px;
  }
 .info-block {
   display: flex;
   align-items: center;
   justify-content: center;
   height: 50px;
   background: #e8f3fe;
   margin: 10px;
   color: #174cfc
 }
  .blank-block {
    width: 10%;
  }
 .text {
   font-size: 14px;
 }
 .item {
   margin-bottom: 4px;
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
   margin: 2px;
 }
</style>
