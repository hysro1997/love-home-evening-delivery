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
      <el-form-item label="考勤教师的id数组" prop="baseCheckInTeachers">
        <el-input
          v-model="queryParams.baseCheckInTeachers"
          placeholder="请输入考勤教师的id数组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤开始日期" prop="baseCheckInBeginDate">
        <el-date-picker clearable
          v-model="queryParams.baseCheckInBeginDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择考勤开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="考勤结束日期" prop="baseCheckInEndDate">
        <el-date-picker clearable
          v-model="queryParams.baseCheckInEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择考勤结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="每周考勤天数" prop="baseCheckInDaysOneWeek">
        <el-input
          v-model="queryParams.baseCheckInDaysOneWeek"
          placeholder="请输入每周考勤天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤总天数" prop="baseCheckInSumDays">
        <el-input
          v-model="queryParams.baseCheckInSumDays"
          placeholder="请输入考勤总天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤周数" prop="baseCheckInWeeks">
        <el-input
          v-model="queryParams.baseCheckInWeeks"
          placeholder="请输入考勤周数"
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

    <el-table v-loading="loading" :data="baseCheckinList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="baseCheckInId" />
      <el-table-column label="考勤名称" align="center" prop="baseCheckInName" />
      <el-table-column label="考勤学生的id数组" align="center" prop="baseCheckInStudents" />
      <el-table-column label="考勤教师的id数组" align="center" prop="baseCheckInTeachers" />
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
      <el-table-column label="考勤周数" align="center" prop="baseCheckInWeeks" />
      <el-table-column label="考勤状态" align="center" prop="baseCheckInStatus" />
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="考勤名称" prop="baseCheckInName">
          <el-input v-model="form.baseCheckInName" placeholder="请输入考勤名称" />
        </el-form-item>
        <el-form-item label="考勤学生的id数组" prop="baseCheckInStudents">
          <el-input v-model="form.baseCheckInStudents" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="考勤教师的id数组" prop="baseCheckInTeachers">
          <el-input v-model="form.baseCheckInTeachers" placeholder="请输入考勤教师的id数组" />
        </el-form-item>
        <el-form-item label="考勤开始日期" prop="baseCheckInBeginDate">
          <el-date-picker clearable
            v-model="form.baseCheckInBeginDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择考勤开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考勤结束日期" prop="baseCheckInEndDate">
          <el-date-picker clearable
            v-model="form.baseCheckInEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择考勤结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="每周考勤天数" prop="baseCheckInDaysOneWeek">
          <el-input v-model="form.baseCheckInDaysOneWeek" placeholder="请输入每周考勤天数" />
        </el-form-item>
        <el-form-item label="考勤总天数" prop="baseCheckInSumDays">
          <el-input v-model="form.baseCheckInSumDays" placeholder="请输入考勤总天数" />
        </el-form-item>
        <el-form-item label="考勤周数" prop="baseCheckInWeeks">
          <el-input v-model="form.baseCheckInWeeks" placeholder="请输入考勤周数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBaseCheckin, getBaseCheckin, delBaseCheckin, addBaseCheckin, updateBaseCheckin } from "@/api/checkin/baseCheckin";

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
        baseCheckInStudents: null,
        baseCheckInTeachers: null,
        baseCheckInBeginDate: null,
        baseCheckInEndDate: null,
        baseCheckInDaysOneWeek: null,
        baseCheckInSumDays: null,
        baseCheckInWeeks: null,
        baseCheckInStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询考勤总表列表 */
    getList() {
      this.loading = true;
      listBaseCheckin(this.queryParams).then(response => {
        this.baseCheckinList = response.rows;
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
        baseCheckInId: null,
        baseCheckInName: null,
        baseCheckInStudents: null,
        baseCheckInTeachers: null,
        baseCheckInBeginDate: null,
        baseCheckInEndDate: null,
        baseCheckInDaysOneWeek: null,
        baseCheckInSumDays: null,
        baseCheckInWeeks: null,
        baseCheckInStatus: null
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
      this.ids = selection.map(item => item.baseCheckInId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加考勤总表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const baseCheckInId = row.baseCheckInId || this.ids
      getBaseCheckin(baseCheckInId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考勤总表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
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
      }, `baseCheckin_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
