<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="teacherCheckinStatisticQueryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="对应考勤总表id" prop="baseCheckInId">
        <el-input
          v-model="queryParams.baseCheckInId"
          placeholder="请输入对应考勤总表id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="考勤开始日期" prop="checkInBeginDate">
        <el-date-picker clearable
          v-model="queryParams.checkInBeginDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择考勤开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="考勤结束日期" prop="checkInEndDate">
        <el-date-picker clearable
          v-model="queryParams.checkInEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择考勤结束日期">
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
          v-hasPermi="['checkin:teacherCheckinStatistic:add']"
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
          v-hasPermi="['checkin:teacherCheckinStatistic:edit']"
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
          v-hasPermi="['checkin:teacherCheckinStatistic:remove']"
        >删除</el-button>
      </el-col -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['checkin:teacherCheckinStatistic:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherCheckinStatisticList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="对应考勤总表id" align="center" prop="baseCheckInId" />
      <el-table-column label="员工id" align="center" prop="teacherId" />
      <el-table-column label="员工姓名" align="center" prop="teacherName" />
      <el-table-column label="考勤总天数" align="center" prop="checkInSumDays" />
      <el-table-column label="考勤次数" align="center" prop="checkInTimes" />
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
      <el-table-column label="请假天数" align="center" prop="leaveDays" />
      <el-table-column label="值班次数" align="center" prop="onDutyTimes" />
      <el-table-column label="账单状态" align="center" prop="billStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.billStatus === 0">未生成</el-tag><el-tag type="success" v-else-if="scope.row.billStatus === 1">已生成</el-tag><el-tag type="warning" v-else>已作废</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="工资付款状态" align="center" prop="paymentStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.paymentStatus === 0">未结清</el-tag><el-tag type="success" v-else-if="scope.row.billStatus === 1">已结清</el-tag><el-tag type="warning" v-else>默认</el-tag>
        </template>
      </el-table-column>
      <!-- el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['checkin:teacherCheckinStatistic:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['checkin:teacherCheckinStatistic:remove']"
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

    <!-- 添加或修改员工考勤汇总对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="teacherCheckinStatisticForm" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="对应考勤总表id" prop="baseCheckInId">
          <el-input v-model="form.baseCheckInId" placeholder="请输入对应考勤总表id" />
        </el-form-item>
        <el-form-item label="员工id" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入员工id" />
        </el-form-item>
        <el-form-item label="员工姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入员工姓名" />
        </el-form-item>
        <el-form-item label="考勤总天数" prop="checkInSumDays">
          <el-input v-model="form.checkInSumDays" placeholder="请输入考勤总天数" />
        </el-form-item>
        <el-form-item label="考勤次数" prop="checkInTimes">
          <el-input v-model="form.checkInTimes" placeholder="请输入考勤次数" />
        </el-form-item>
        <el-form-item label="考勤开始日期" prop="checkInBeginDate">
          <el-date-picker clearable
            v-model="form.checkInBeginDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择考勤开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考勤结束日期" prop="checkInEndDate">
          <el-date-picker clearable
            v-model="form.checkInEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择考勤结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="请假天数" prop="leaveDays">
          <el-input v-model="form.leaveDays" placeholder="请输入请假天数" />
        </el-form-item>
        <el-form-item label="值班次数" prop="onDutyTimes">
          <el-input v-model="form.onDutyTimes" placeholder="请输入值班次数" />
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
import { listTeacherCheckinStatistic, getTeacherCheckinStatistic, delTeacherCheckinStatistic, addTeacherCheckinStatistic, updateTeacherCheckinStatistic } from "@/api/checkin/teacherCheckinStatistic";

export default {
  name: "TeacherCheckinStatistic",
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
      // 员工考勤汇总表格数据
      teacherCheckinStatisticList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        baseCheckInId: null,
        teacherId: null,
        teacherName: null,
        checkInSumDays: null,
        checkInTimes: null,
        checkInBeginDate: null,
        checkInEndDate: null,
        leaveDays: null,
        onDutyTimes: null,
        billStatus: null,
        paymentStatus: null
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
    /** 查询员工考勤汇总列表 */
    getList() {
      this.loading = true;
      listTeacherCheckinStatistic(this.queryParams).then(response => {
        this.teacherCheckinStatisticList = response.rows;
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
        id: null,
        baseCheckInId: null,
        teacherId: null,
        teacherName: null,
        checkInSumDays: null,
        checkInTimes: null,
        checkInBeginDate: null,
        checkInEndDate: null,
        leaveDays: null,
        onDutyTimes: null,
        billStatus: null,
        paymentStatus: null
      };
      this.resetForm("teacherCheckinStatisticForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("teacherCheckinStatisticQueryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工考勤汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTeacherCheckinStatistic(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工考勤汇总";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["teacherCheckinStatisticForm"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTeacherCheckinStatistic(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacherCheckinStatistic(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除员工考勤汇总编号为"' + ids + '"的数据项？').then(function() {
        return delTeacherCheckinStatistic(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('checkin/teacherCheckinStatistic/export', {
        ...this.queryParams
      }, `teacherCheckinStatistic_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
