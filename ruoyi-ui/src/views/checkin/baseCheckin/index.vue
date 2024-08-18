<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考勤名称" prop="baseCheckInName">
        <el-input
          v-model="queryParams.baseCheckInName"
          placeholder="请输入考勤名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['checkin:baseCheckin:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['checkin:baseCheckin:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['checkin:baseCheckin:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['checkin:baseCheckin:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="baseCheckinList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="baseCheckInId" />
      <el-table-column label="考勤名称" align="center" prop="baseCheckInName" />
      <el-table-column label="考勤学生" align="center" prop="students">
        <tempalte slot-scope="scope">
          <el-popover
            placement="right"
            title="考勤学生"
            width="200"
            trigger="click"
            v-if="null !== scope.row.students">
            <el-table :data="scope.row.students">
              <el-table-column width="150" property="studentName" label="姓名"></el-table-column>
            </el-table>
            <el-button slot="reference">
              <span>{{scope.row.students[0].studentName}}...</span>
            </el-button>
          </el-popover>
          <!--span-- v-for="item in scope.row.students">{{item.studentName}}</span-->
        </tempalte>
      </el-table-column>
      <el-table-column label="考勤员工" align="center" prop="teachers">
        <tempalte slot-scope="scope">
          <el-popover
            placement="right"
            title="考勤员工"
            width="200"
            trigger="click"
            v-if="null !== scope.row.teachers">
            <el-table :data="scope.row.teachers">
              <el-table-column width="150" property="teacherName" label="姓名"></el-table-column>
            </el-table>
            <el-button slot="reference">
              <span>{{scope.row.teachers[0].teacherName}}...</span>
            </el-button>
          </el-popover>
        </tempalte>
      </el-table-column>
      <el-table-column label="考勤开始日期" align="center" prop="baseCheckInBeginDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.baseCheckInBeginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考勤结束日期" align="center" prop="baseCheckInEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.baseCheckInEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="每周考勤天数" align="center" prop="baseCheckInDaysOneWeek" />
      <el-table-column label="考勤总天数" align="center" prop="baseCheckInSumDays" />
      <!-- el-table-column label="考勤周数" align="center" prop="baseCheckInWeeks" /-->
      <el-table-column label="考勤状态" align="center" prop="baseCheckInStatus">
        <template slot-scope="scope">
          <span>{{ scope.row.baseCheckInStatus === 0 ? "未开始" : scope.row.baseCheckInStatus === 1 ? "考勤中" : "考勤结束" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['checkin:baseCheckin:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['checkin:baseCheckin:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改考勤总表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" :closeOnClickModal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-steps :active="active" finish-status="success">
          <el-step title="考勤时间"></el-step>
          <el-step title="学生考勤名单"></el-step>
          <el-step title="确认考勤"></el-step>
        </el-steps>
        <div v-show="0 === active">
          <el-row>
            <el-col :span="12">
              <el-form-item label="考勤名称" prop="baseCheckInName">
                <el-input v-model="form.baseCheckInName" placeholder="请输入考勤名称" />
              </el-form-item>
            </el-col>
          </el-row>
          <div v-show="form.baseCheckInName">
            <el-row :gutter="10">
              <el-col :span="12">
                <el-form-item label="考勤开始日期" prop="baseCheckInBeginDate">
                  <el-date-picker clearable
                                  v-model="form.baseCheckInBeginDate"
                                  type="date"
                                  placeholder="请选择考勤开始日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="考勤结束日期" prop="baseCheckInEndDate">
                  <el-date-picker clearable
                                  v-model="form.baseCheckInEndDate"
                                  type="date"
                                  placeholder="请选择考勤结束日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row v-show="form.baseCheckInBeginDate && form.baseCheckInEndDate">
              <el-col>
                <el-form-item label="周几考勤：" prop="baseCheckWeekenDay">
                  <el-checkbox-group v-model="baseCheckWeekenDay">
                    <el-checkbox-button :label="1">星期一</el-checkbox-button>
                    <el-checkbox-button :label="2">星期二</el-checkbox-button>
                    <el-checkbox-button :label="3">星期三</el-checkbox-button>
                    <el-checkbox-button :label="4">星期四</el-checkbox-button>
                    <el-checkbox-button :label="5">星期五</el-checkbox-button>
                    <el-checkbox-button :label="6">星期六</el-checkbox-button>
                    <el-checkbox-button :label="0">星期日</el-checkbox-button>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row v-show="form.baseCheckInBeginDate && form.baseCheckInEndDate">
              <el-col>
                <el-form-item  label="周几考勤快捷键">
                  <el-button @click="baseCheckWeekenDay = [1,2,3,4,5]">工作日</el-button><el-button @click="baseCheckWeekenDay = [1,2,3,4,5,6]">6天</el-button><el-button @click="baseCheckWeekenDay = [0,1,2,3,4,5,6]">整周</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </div>
          <el-row v-show="baseCheckWeekenDay.length > 0">
            <el-col :span="10">
              <el-form-item label="每周考勤天数" prop="baseCheckInDaysOneWeek">
                <el-input @input="form.baseCheckInDaysOneWeek = form.baseCheckInDaysOneWeek.replace(/[^\d]/g,'')" style="width: 120px" :disabled="disableBaseCheckInDaysOneWeek" v-model="form.baseCheckInDaysOneWeek" /><el-button type="text" @click="disableBaseCheckInDaysOneWeek=!disableBaseCheckInDaysOneWeek">手动修改天数</el-button>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="考勤总天数" prop="baseCheckInSumDays">
                <el-input @input="form.baseCheckInSumDays = form.baseCheckInSumDays.replace(/[^\d]/g,'')" style="width: 120px" :disabled="disableBaseCheckInSumDays" v-model="form.baseCheckInSumDays" /><el-button type="text" @click="disableBaseCheckInSumDays=!disableBaseCheckInSumDays">手动修改天数</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <div v-show="1 === active">
          <el-form-item label="考勤学生" prop="baseCheckInStudents">
            <el-transfer
              style="text-align: left; display: inline-block"
              v-model="form.checkinStudents"
              :titles="['学生列表', '考勤']"
              :button-texts="['取消考勤','进入考勤']"
              :data="studentsInSchool"
              :props="{key:'studentId',label:'studentName',disabled:false}">
              <span slot-scope="{ option }">{{ option.studentGrade }} - {{ option.studentName }}</span>
            </el-transfer>
          </el-form-item>
        </div>
        <!-- div v-show="2 === active">
          <el-form-item label="考勤员工" prop="baseCheckInTeachers">
            <el-transfer
              style="text-align: left; display: inline-block"
              v-model="form.checkinTeachers"
              :titles="['员工列表', '考勤']"
              :button-texts="['取消考勤','进入考勤']"
              :data="teachersInSchool"
              :props="{key:'teacherId',label:'teacherName',disabled:false}">
            </el-transfer>
          </el-form-item>
        </div -->
        <div v-show="2 === active">
          <el-descriptions title="考勤信息确认列表" direction="vertical" :column="4" border>
            <el-descriptions-item label="考勤名称" :span="2">{{form.baseCheckInName}}</el-descriptions-item>
            <el-descriptions-item label="考勤总天数" :span="2">{{form.baseCheckInSumDays}}</el-descriptions-item>
            <el-descriptions-item label="开始日期" :span="1">{{confirmBeginDate}}</el-descriptions-item>
            <el-descriptions-item label="结束日期" :span="1">{{confirmEndDate}}</el-descriptions-item>
            <el-descriptions-item label="每周考勤天数" :span="2">
              <el-checkbox-group v-model="form.baseCheckWeekenDay" disabled>
                <el-checkbox :label="1">星期一</el-checkbox>
                <el-checkbox :label="2">星期二</el-checkbox>
                <el-checkbox :label="3">星期三</el-checkbox>
                <el-checkbox :label="4">星期四</el-checkbox>
                <el-checkbox :label="5">星期五</el-checkbox>
                <el-checkbox :label="6">星期六</el-checkbox>
                <el-checkbox :label="0">星期日</el-checkbox>
              </el-checkbox-group>
            </el-descriptions-item>
            <el-descriptions-item label="考勤学生" :span="2">
              <el-tag effect="light" style="margin-right:5px" v-for="item in confirmStudentNames">{{item}}&nbsp;</el-tag>
            </el-descriptions-item>

            <!-- el-descriptions-item label="考勤老师" :span="2">
              <el-tag effect="plain" style="margin-right:5px" v-for="item in confirmTeacherNames">{{item}}&nbsp;</el-tag>
            </el-descriptions-item -->
          </el-descriptions>
        </div>
        <!-- el-form-item label="考勤周数" prop="baseCheckInWeeks">
          <el-input v-model="form.baseCheckInWeeks" placeholder="请输入考勤周数" />
        </el-form-item -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" icon="el-icon-arrow-left" @click="previous" v-show="1 === active || 2 === active">上一步</el-button>
        <el-button style="margin-top: 12px;" @click="next" v-show="0 === active || 1 === active">下一步<i class="el-icon-arrow-right el-icon--right"></i></el-button>
        <el-button type="primary" @click="submitForm" v-show="2 === active">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
<script>
import { listBaseCheckin, getBaseCheckin, delBaseCheckin, addBaseCheckin, updateBaseCheckin, getInSchoolStudents, getInSchoolTeachers } from "@/api/checkin/baseCheckin";

export default {
  name: "BaseCheckin",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 考勤总表表格数据
      baseCheckinList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        baseCheckInName: null,
        baseCheckInStudents: [],
        baseCheckInTeachers: [],
        baseCheckInBeginDate: null,
        baseCheckInEndDate: null,
        baseCheckInDaysOneWeek: null,
        baseCheckInSumDays: null,
        baseCheckInWeeks: null,
        baseCheckInStatus: null,
        ajHomoInBaseCheckIns: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      disableBaseCheckInDaysOneWeek: true,
      disableBaseCheckInSumDays: true,
      baseCheckWeekenDay: [],
      studentsInSchool: [],
      teachersInSchool: [],
      active: 0,
      confirmBeginDate: null,
      confirmEndDate: null,
      confirmStudentNames: [],
      confirmTeacherNames: []
    };
  },
  created() {
    this.getList();
    this.getStudentsAndTeachers();
  },
  watch: {
    baseCheckWeekenDay: {
      immediate: false,
      handler() {
        if (this.baseCheckWeekenDay.length !== 0){
          this.calculateCheckInInfo();
        } else {
          this.form.baseCheckInDaysOneWeek = null;
          this.form.baseCheckInSumDays = null;
        }
      }
    }
  },
  methods: {
    tableRowClassName({row, rowIndex}) {
      if (rowIndex % 2 === 1) {
        return 'warning-row';
      } else if (rowIndex % 2 === 0) {
        return 'success-row';
      }
      return '';
    },
    previous(){
      if (this.active > 0) {
        this.active -- ;
      }
    },
    next() {
      let that = this;
      switch (this.active) {
        case 0:
          if(!this.form.baseCheckInName){
            this.$modal.msgWarning("没有输入考勤名称");
            return;
          }
          if(!this.form.baseCheckInBeginDate){
            this.$modal.msgWarning("没有选择考勤开始日期");
            this.form.baseCheckInDaysOneWeek = null;
            this.form.baseCheckInSumDays = null;
            this.baseCheckWeekenDay = [];
            return;
          }
          if(!this.form.baseCheckInEndDate){
            this.$modal.msgWarning("没有选择考勤结束日期");
            this.form.baseCheckInDaysOneWeek = null;
            this.form.baseCheckInSumDays = null;
            this.baseCheckWeekenDay = [];
            return;
          }
          if (this.form.baseCheckInEndDate < this.form.baseCheckInBeginDate) {
            this.$modal.msgWarning("结束日期比开始日期更早");
            return;
          }
          if(!this.baseCheckWeekenDay || this.baseCheckWeekenDay.length === 0){
            this.$modal.msgWarning("没有选择考勤天");
            return;
          }
          if(!this.form.baseCheckInDaysOneWeek){
            this.$modal.msgWarning("没有生成或输入每周考勤天数");
            return;
          }
          if(!this.form.baseCheckInDaysOneWeek){
            this.$modal.msgWarning("没有生成或输入每周考勤天数");
            return;
          }
          if(!this.form.baseCheckInSumDays){
            this.$modal.msgWarning("没有生成或输入考勤总天数");
            return;
          }
          this.confirmBeginDate = this.form.baseCheckInBeginDate.toLocaleDateString();
          this.confirmEndDate = this.form.baseCheckInEndDate.toLocaleDateString();
          break;
        case 1:
          if(this.form.checkinStudents <= 0){
            this.$modal.msgWarning("没有选择考勤的学生");
            break;
          }
          that.confirmStudentNames = [];
          this.form.checkinStudents.forEach(function(e) {
            that.studentsInSchool.forEach(function(a){
              if (a.studentId === e){
                that.confirmStudentNames.push(a.studentName);
              }
            });
          });
          break;
          /** case 2:
           if(this.form.checkinTeachers <= 0){
            this.$modal.msgWarning("没有选择考勤的老师");
            break;
          }

          that.confirmTeacherNames = [];
          this.form.checkinTeachers.forEach(function(e) {
            that.teachersInSchool.forEach(function(a){
              if (a.teacherId === e){
                that.confirmTeacherNames.push(a.teacherName);
              }
            });
          });
          break;
           */
        default:
          break;
      }
      this.active ++;
    },
    /** 获取在学在岗的学生老师 */
    getStudentsAndTeachers(){
      let that = this;
      getInSchoolStudents().then(response => {
        this.studentsInSchool = response.data;
      });
      getInSchoolTeachers().then(response => {
        this.teachersInSchool = response.data;
      });
    },
    /** 计算考勤信息 */
    calculateCheckInInfo(){
      if (null !== this.form.baseCheckInBeginDate) {
        if (null !== this.form.baseCheckInEndDate) {
          if (this.form.baseCheckInEndDate < this.form.baseCheckInBeginDate){
          this.$modal.msgWarning("结束日期比开始日期更早");
          return;
        }
          if (0 < this.baseCheckWeekenDay.length) {
            let sumDays = 0;
            let startWeekDay = new Date(this.form.baseCheckInBeginDate);
            let endWeekDay = new Date(this.form.baseCheckInEndDate);
              this.form.baseCheckInDaysOneWeek = this.baseCheckWeekenDay.length;
              while (startWeekDay <= endWeekDay){
                if (0 <= this.baseCheckWeekenDay.indexOf(startWeekDay.getDay())){
                  sumDays++;
                }
                startWeekDay.setDate(startWeekDay.getDate() + 1);
              }
            this.form.baseCheckInSumDays = sumDays;
            this.form.baseCheckWeekenDay = this.baseCheckWeekenDay;
          } else {
            this.$modal.msgWarning("没有设置每周考勤天数");
            this.form.baseCheckInSumDays = null;
          }
        } else {
          this.$modal.msgWarning("没有选择考勤结束日期");
        }
      } else {
        this.$modal.msgWarning("没有选择考勤开始日期");
      }
    },
    /** 查询考勤总表列表 */
    getList() {
      this.loading = true;
      let that = this;
      listBaseCheckin(this.queryParams).then(response => {
        that.baseCheckinList = response.rows;
        if (this.baseCheckinList.length > 0){
          that.baseCheckinList.forEach(function(e) {
            e.students = [];
            e.teachers = [];
            e.ajHomoInBaseCheckIns.forEach(function(element) {
              if (null !== element.studentId){
                e.students.push(element)
              } else {
                e.teachers.push(element);
              }
            });
            if (0 === e.students.length){
              e.students = null;
            }
            if (0 === e.teachers.length){
              e.teachers = null;
            }
          })
        } else {
          this.baseCheckinList = null;
        }
        that.total = response.total;
        that.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        baseCheckInId: null,
        baseCheckInName: null,
        baseCheckInStudents: null,
        baseCheckInTeachers: null,
        baseCheckInBeginDate: null,
        baseCheckInEndDate: null,
        baseCheckInDaysOneWeek: null,
        baseCheckInSumDays: null,
        baseCheckInWeeks: null,
        baseCheckInStatus: 0,
        ajHomoInBaseCheckIns: null,
        students: null,
        teachers: null,
        checkinStudents: [],
        checkinTeachers: [],
        baseCheckWeekenDay: [],
      };
      this.confirmStudentNames = [];
      this.confirmTeacherNames = [];
      this.active = 0;
      this.baseCheckWeekenDay = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.baseCheckInId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      //this.getStudentsAndTeachers();
      this.reset();
      this.open = true;
      this.title = "添加考勤总表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      //this.getStudentsAndTeachers();
      this.reset();
      let that = this;
      const baseCheckInId = row.baseCheckInId || this.ids
      getBaseCheckin(baseCheckInId).then(response => {
        let temp = response.data;
        temp.students = [];
        temp.teachers = [];
        temp.checkinStudents = [];
        temp.checkinTeachers = [];
        that.baseCheckWeekenDay = temp.baseCheckWeekenDay;
        temp.baseCheckInBeginDate = new Date(temp.baseCheckInBeginDate);
        temp.baseCheckInEndDate = new Date(temp.baseCheckInEndDate);
        temp.ajHomoInBaseCheckIns.forEach(function(element) {
          if (null !== element.studentId){
            temp.students.push(element);
            temp.checkinStudents.push(element.studentId);
          } else {
            temp.teachers.push(element);
            temp.checkinTeachers.push(element.teacherId);
          }
        });
        if (0 === temp.students.length){
          temp.students = null;
        }
        if (0 === temp.teachers.length){
          temp.teachers = null;
        }
        that.form = temp;
        that.open = true;
        that.title = "修改考勤总表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.baseCheckInBeginDate = this.form.baseCheckInBeginDate.setDate(this.form.baseCheckInBeginDate.getDate());
          this.form.baseCheckInEndDate = this.form.baseCheckInEndDate.setDate(this.form.baseCheckInEndDate.getDate());
          if (this.form.baseCheckInId != null) {
            updateBaseCheckin(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBaseCheckin(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const baseCheckInIds = row.baseCheckInId || this.ids;
      this.$modal.confirm('是否确认删除考勤总表编号为"' + baseCheckInIds + '"的数据项？').then(function() {
        return delBaseCheckin(baseCheckInIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('checkin/baseCheckin/export', {
        ...this.queryParams
      }, `考勤总表_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
