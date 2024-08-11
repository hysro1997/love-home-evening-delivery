<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师姓名" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入教师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师电话" prop="teacherPhone">
        <el-input
          v-model="queryParams.teacherPhone"
          placeholder="请输入教师电话"
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
          v-hasPermi="['info:teacherInfo:add']"
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
          v-hasPermi="['info:teacherInfo:edit']"
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
          v-hasPermi="['info:teacherInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['info:teacherInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherInfoList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="teacherId" />
      <el-table-column label="教师姓名" align="center" prop="teacherName" />
      <el-table-column label="教师性别" align="center" prop="teacherGender">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.teacherGender===1">男</el-tag><el-tag v-if="scope.row.teacherGender===2" type="danger">女</el-tag><el-tag v-if="scope.row.teacherGender===0" type="info">默认</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="教师电话" align="center" prop="teacherPhone" />
      <el-table-column label="用工类别" align="center" prop="teacherEmployType">
        <template slot-scope="scope">
          {{scope.row.teacherEmployType === 0 ? "兼职" : scope.row.teacherEmployType === 1 ? "全职" : scope.row.teacherEmployType === 2 ? "实习生" : "暑假工"}}
        </template>
      </el-table-column>
      <el-table-column label="在职状态" align="center" prop="teacherStatus">
        <template slot-scope="scope">
          <el-tag effect="plain" type="success" v-if="scope.row.teacherStatus === 0">
            在职
          </el-tag>
          <el-tag effect="plain" type="warning" v-else>
            离职
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['info:teacherInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleTeacherUpdate(scope.row)"
            v-hasPermi="['info:teacherInfo:edit']"
          >{{scope.row.teacherStatus === 0 ? "使离职" : "使复职"}}</el-button>
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

    <!-- 添加或修改教师信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :closeOnClickModal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教师姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入教师姓名" />
        </el-form-item>
        <el-form-item label="教师性别" prop="teacherGender">
          <template>
            <el-radio v-model="form.teacherGender" label="1">男</el-radio>
            <el-radio v-model="form.teacherGender" label="2">女</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="教师电话" prop="teacherPhone">
          <el-input v-model="form.teacherPhone" placeholder="请输入教师电话" maxlength="11"/>
        </el-form-item>
        <el-form-item label="用工类别" prop="teacherEmployType">
          <template>
            <el-radio v-model="form.teacherEmployType" label="1">全职</el-radio>
            <el-radio v-model="form.teacherEmployType" label="0">兼职</el-radio>
            <el-radio v-model="form.teacherEmployType" label="2">实习生</el-radio>
            <el-radio v-model="form.teacherEmployType" label="3">暑假工</el-radio>
          </template>
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
import { listTeacherInfo, getTeacherInfo, delTeacherInfo, addTeacherInfo, updateTeacherInfo } from "@/api/info/teacherInfo";

export default {
  name: "TeacherInfo",
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
      // 教师信息表格数据
      teacherInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherName: null,
        teacherGender: null,
        teacherPhone: null,
        teacherEmployType: null,
        teacherFace: null,
        teacherStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherName: [
          { required: true, message: "教师姓名不能为空", trigger: "blur" }
        ],
        teacherPhone: [
          { required: true, message: "教师电话不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
      }
    };
  },
  created() {
    this.getList();
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
    /** 查询教师信息列表 */
    getList() {
      this.loading = true;
      listTeacherInfo(this.queryParams).then(response => {
        this.teacherInfoList = response.rows;
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
        teacherId: null,
        teacherName: null,
        teacherGender: null,
        teacherPhone: null,
        teacherEmployType: null,
        teacherFace: null,
        teacherStatus: null
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
      this.ids = selection.map(item => item.teacherId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教师信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teacherId = row.teacherId || this.ids
      getTeacherInfo(teacherId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改教师信息";
      });
    },
    handleTeacherUpdate(row){
      let data = row;
      data.teacherStatus = row.teacherStatus === 0 ? 1 : 0;
      updateTeacherInfo(data).then(response => {
        this.$modal.msgSuccess("操作成功");
        this.open = false;
        this.getList();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.teacherName = this.form.teacherName.trim();
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teacherId != null) {
            updateTeacherInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacherInfo(this.form).then(response => {
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
      const teacherIds = row.teacherId || this.ids;
      this.$modal.confirm('是否确认删除教师信息编号为"' + teacherIds + '"的数据项？').then(function() {
        return delTeacherInfo(teacherIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('info/teacherInfo/export', {
        ...this.queryParams
      }, `教师信息_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
