<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生学校" prop="studentSchool">
        <el-select v-model="queryParams.studentSchool" placeholder="请选择学校"
                   @change="handleQuery">
          <el-option
            v-for="item in schools"
            :key="item.id"
            :label="item.schoolName"
            :value="item.schoolName">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生年级" prop="studentGrade">
        <el-select v-model="queryParams.studentGrade" placeholder="请选择年级"
                   @change="handleQuery">
          <el-option
            v-for="item in grades"
            :key="item.id"
            :label="item.grade"
            :value="item.grade">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生班级" prop="studentClass">
        <el-select v-model="queryParams.studentClass" placeholder="请选择班级"
                   @change="handleQuery">
          <el-option
            v-for="item in studentClasses"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="联系电话" prop="studentPhone">
        <el-input
          v-model="queryParams.studentPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生来源" prop="studentSource">
        <el-select v-model="queryParams.studentSource" placeholder="请选择学生来源"
                   @change="handleQuery">
          <el-option
            v-for="item in studentSource"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
          v-hasPermi="['info:studentInfo:add']"
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
          v-hasPermi="['info:studentInfo:edit']"
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
          v-hasPermi="['info:studentInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['info:studentInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentInfoList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="studentId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="学生性别" align="center" prop="studentGender">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.studentGender===1">男</el-tag><el-tag v-if="scope.row.studentGender===2" type="danger">女</el-tag><el-tag v-if="scope.row.studentGender===0" type="info">默认</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="学生学校" align="center" prop="studentSchool" />
      <el-table-column label="学生年级" align="center" prop="studentGrade" />
      <el-table-column label="学生班级" align="center" prop="studentClass" />
      <el-table-column label="联系电话" align="center" prop="studentPhone" />
      <el-table-column label="学生来源" align="center" prop="studentSource" />
      <el-table-column label="备注信息" align="center" prop="studentRemark" />
      <!-- el-table-column-- label="学生状态" align="center" prop="studentStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.studentStatus===0" type="success">在学</el-tag><el-tag v-if="scope.row.studentStatus===1" type="warning">退学</el-tag>
        </template>
      </el-table-column -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['info:studentInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['info:studentInfo:remove']"
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

    <!-- 添加或修改学生信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :closeOnClickModal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名（如果重复，请添加后缀）" />
        </el-form-item>
        <el-form-item label="学生性别" prop="studentGender">
          <template>
            <el-radio v-model="form.studentGender" label="1">男</el-radio>
            <el-radio v-model="form.studentGender" label="2">女</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="学生学校" prop="studentSchool">
          <el-select v-model="form.studentSchool" placeholder="请选择学校">
            <el-option
              v-for="item in schools"
              :key="item.id"
              :label="item.schoolName"
              :value="item.schoolName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生年级" prop="studentGrade">
          <el-select v-model="form.studentGrade" placeholder="请选择年级">
            <el-option
              v-for="item in grades"
              :key="item.id"
              :label="item.grade"
              :value="item.grade">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生班级" prop="studentClass">
          <el-select v-model="form.studentClass" placeholder="请选择班级">
            <el-option
              v-for="item in studentClasses"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" prop="studentPhone">
          <el-input v-model="form.studentPhone" placeholder="请输入联系电话" maxlength="11"/>
        </el-form-item>
        <el-form-item label="学生来源" prop="studentSource">
          <el-select v-model="form.studentSource" placeholder="请选择学生来源">
            <el-option
              v-for="item in studentSource"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注信息" prop="studentRemark">
          <el-input v-model="form.studentRemark" type="textarea" :rows="2" placeholder="请输入备注信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
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
import { listStudentInfo, getStudentInfo, delStudentInfo, addStudentInfo, updateStudentInfo } from "@/api/info/studentInfo";
import { getGrades, getSchools } from '../../../api/info/studentInfo'

export default {
  name: "StudentInfo",
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
      // 学生信息表格数据
      studentInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentName: null,
        studentGender: null,
        studentSchool: null,
        studentGrade: null,
        studentClass: null,
        studentPhone: null,
        studentFace: null,
        studentSource: null,
        studentStatus: null
      },
      // 表单参数
      form: {},
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
    };
  },
  created() {
    this.getList();
    this.initGrades();
    this.initSchools();
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
    /** 查询学生信息列表 */
    getList() {
      this.loading = true;
      listStudentInfo(this.queryParams).then(response => {
        this.studentInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
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
      this.ids = selection.map(item => item.studentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const studentId = row.studentId || this.ids
      getStudentInfo(studentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.studentName = this.form.studentName.trim();
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.studentId != null) {
            updateStudentInfo(this.form).then(response => {
              console.log(response)
              if (response.data !== 2){
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.$modal.msgWarning("学生姓名重复");
              }
            });
          } else {
            addStudentInfo(this.form).then(response => {
              if (response.data !== 2){
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.$modal.msgWarning("学生姓名重复");
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const studentIds = row.studentId || this.ids;
      this.$modal.confirm('是否确认学生信息编号为"' + studentIds + '"的数据项？').then(function() {
        return delStudentInfo(studentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('info/studentInfo/export', {
        ...this.queryParams
      }, `学员信息_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
