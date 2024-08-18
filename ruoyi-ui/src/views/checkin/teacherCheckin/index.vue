<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="员工id" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入员工id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="员工姓名" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入员工姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签到日期" prop="checkInDate">
        <el-date-picker clearable
          v-model="queryParams.checkInDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择签到日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['checkin:teacherCheckin:add']"
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
          v-hasPermi="['checkin:teacherCheckin:edit']"
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
          v-hasPermi="['checkin:teacherCheckin:remove']"
        >删除</el-button>
      </el-col -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['checkin:teacherCheckin:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherCheckinList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="teacherCheckInId" />
      <el-table-column label="员工id" align="center" prop="teacherId" />
      <el-table-column label="员工姓名" align="center" prop="teacherName" />
      <el-table-column label="签到日期" align="center" prop="checkInDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkInDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考勤状态" align="center" prop="checkInStatus" />
      <el-table-column label="签到时间" align="center" prop="checkInDatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkInDatetime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当日值班次数" align="center" prop="checkInDayOnDutyTimes" />
      <!-- el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['checkin:teacherCheckin:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['checkin:teacherCheckin:remove']"
          >删除</el-button>
        </template>
      </el-table-column -->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改员工考勤对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工id" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入员工id" />
        </el-form-item>
        <el-form-item label="员工姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入员工姓名" />
        </el-form-item>
        <el-form-item label="签到日期" prop="checkInDate">
          <el-date-picker clearable
            v-model="form.checkInDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择签到日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="签到时间" prop="checkInDatetime">
          <el-date-picker clearable
            v-model="form.checkInDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择签到时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="当日值班次数" prop="checkInDayOnDutyTimes">
          <el-input v-model="form.checkInDayOnDutyTimes" placeholder="请输入当日值班次数" />
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
import { listTeacherCheckin, getTeacherCheckin, delTeacherCheckin, addTeacherCheckin, updateTeacherCheckin } from "@/api/checkin/teacherCheckin";

export default {
  name: "TeacherCheckin",
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
      // 员工考勤表格数据
      teacherCheckinList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        teacherName: null,
        checkInDate: null,
        checkInStatus: null,
        checkInDatetime: null,
        checkInDayOnDutyTimes: null
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
    tableRowClassName({row, rowIndex}) {
      if (rowIndex % 2 === 1) {
        return 'warning-row';
      } else if (rowIndex % 2 === 0) {
        return 'success-row';
      }
      return '';
    },
    /** 查询员工考勤列表 */
    getList() {
      this.loading = true;
      listTeacherCheckin(this.queryParams).then(response => {
        this.teacherCheckinList = response.rows;
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
        teacherCheckInId: null,
        teacherId: null,
        teacherName: null,
        checkInDate: null,
        checkInStatus: null,
        checkInDatetime: null,
        checkInDayOnDutyTimes: null
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
      this.ids = selection.map(item => item.teacherCheckInId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工考勤";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teacherCheckInId = row.teacherCheckInId || this.ids
      getTeacherCheckin(teacherCheckInId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工考勤";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teacherCheckInId != null) {
            updateTeacherCheckin(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacherCheckin(this.form).then(response => {
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
      const teacherCheckInIds = row.teacherCheckInId || this.ids;
      this.$modal.confirm('是否确认删除员工考勤编号为"' + teacherCheckInIds + '"的数据项？').then(function() {
        return delTeacherCheckin(teacherCheckInIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('checkin/teacherCheckin/export', {
        ...this.queryParams
      }, `teacherCheckin_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
