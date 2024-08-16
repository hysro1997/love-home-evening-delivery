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
      <el-form-item label="员工考勤统计表id" prop="teacherCheckInStatistciId">
        <el-input
          v-model="queryParams.teacherCheckInStatistciId"
          placeholder="请输入员工考勤统计表id"
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
      <el-form-item label="考勤总天数" prop="checkInSumDays">
        <el-input
          v-model="queryParams.checkInSumDays"
          placeholder="请输入考勤总天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤次数" prop="checkInTimes">
        <el-input
          v-model="queryParams.checkInTimes"
          placeholder="请输入考勤次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="请假天数" prop="leaveDays">
        <el-input
          v-model="queryParams.leaveDays"
          placeholder="请输入请假天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="值班次数" prop="onDutyTimes">
        <el-input
          v-model="queryParams.onDutyTimes"
          placeholder="请输入值班次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="值班补助" prop="onDutyPension">
        <el-input
          v-model="queryParams.onDutyPension"
          placeholder="请输入值班补助"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="全勤" prop="fullCheckIn">
        <el-input
          v-model="queryParams.fullCheckIn"
          placeholder="请输入全勤"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="全勤奖" prop="fullCheckInPension">
        <el-input
          v-model="queryParams.fullCheckInPension"
          placeholder="请输入全勤奖"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其他补助" prop="otherPension">
        <el-input
          v-model="queryParams.otherPension"
          placeholder="请输入其他补助"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工资扣除" prop="deduckMoney">
        <el-input
          v-model="queryParams.deduckMoney"
          placeholder="请输入工资扣除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实际工资" prop="acutalSalary">
        <el-input
          v-model="queryParams.acutalSalary"
          placeholder="请输入实际工资"
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
          v-hasPermi="['payment:teacherSalary:add']"
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
          v-hasPermi="['payment:teacherSalary:edit']"
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
          v-hasPermi="['payment:teacherSalary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['payment:teacherSalary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherSalaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="员工id" align="center" prop="teacherId" />
      <el-table-column label="员工姓名" align="center" prop="teacherName" />
      <el-table-column label="员工考勤统计表id" align="center" prop="teacherCheckInStatistciId" />
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
      <el-table-column label="考勤总天数" align="center" prop="checkInSumDays" />
      <el-table-column label="考勤次数" align="center" prop="checkInTimes" />
      <el-table-column label="请假天数" align="center" prop="leaveDays" />
      <el-table-column label="值班次数" align="center" prop="onDutyTimes" />
      <el-table-column label="值班补助" align="center" prop="onDutyPension" />
      <el-table-column label="全勤" align="center" prop="fullCheckIn" />
      <el-table-column label="全勤奖" align="center" prop="fullCheckInPension" />
      <el-table-column label="其他补助" align="center" prop="otherPension" />
      <el-table-column label="工资扣除" align="center" prop="deduckMoney" />
      <el-table-column label="实际工资" align="center" prop="acutalSalary" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['payment:teacherSalary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['payment:teacherSalary:remove']"
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

    <!-- 添加或修改工资明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工id" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入员工id" />
        </el-form-item>
        <el-form-item label="员工姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入员工姓名" />
        </el-form-item>
        <el-form-item label="员工考勤统计表id" prop="teacherCheckInStatistciId">
          <el-input v-model="form.teacherCheckInStatistciId" placeholder="请输入员工考勤统计表id" />
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
        <el-form-item label="考勤总天数" prop="checkInSumDays">
          <el-input v-model="form.checkInSumDays" placeholder="请输入考勤总天数" />
        </el-form-item>
        <el-form-item label="考勤次数" prop="checkInTimes">
          <el-input v-model="form.checkInTimes" placeholder="请输入考勤次数" />
        </el-form-item>
        <el-form-item label="请假天数" prop="leaveDays">
          <el-input v-model="form.leaveDays" placeholder="请输入请假天数" />
        </el-form-item>
        <el-form-item label="值班次数" prop="onDutyTimes">
          <el-input v-model="form.onDutyTimes" placeholder="请输入值班次数" />
        </el-form-item>
        <el-form-item label="值班补助" prop="onDutyPension">
          <el-input v-model="form.onDutyPension" placeholder="请输入值班补助" />
        </el-form-item>
        <el-form-item label="全勤" prop="fullCheckIn">
          <el-input v-model="form.fullCheckIn" placeholder="请输入全勤" />
        </el-form-item>
        <el-form-item label="全勤奖" prop="fullCheckInPension">
          <el-input v-model="form.fullCheckInPension" placeholder="请输入全勤奖" />
        </el-form-item>
        <el-form-item label="其他补助" prop="otherPension">
          <el-input v-model="form.otherPension" placeholder="请输入其他补助" />
        </el-form-item>
        <el-form-item label="工资扣除" prop="deduckMoney">
          <el-input v-model="form.deduckMoney" placeholder="请输入工资扣除" />
        </el-form-item>
        <el-form-item label="实际工资" prop="acutalSalary">
          <el-input v-model="form.acutalSalary" placeholder="请输入实际工资" />
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
import { listTeacherSalary, getTeacherSalary, delTeacherSalary, addTeacherSalary, updateTeacherSalary } from "@/api/payment/teacherSalary";

export default {
  name: "TeacherSalary",
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
      // 工资明细表格数据
      teacherSalaryList: [],
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
        teacherCheckInStatistciId: null,
        checkInBeginDate: null,
        checkInEndDate: null,
        checkInSumDays: null,
        checkInTimes: null,
        leaveDays: null,
        onDutyTimes: null,
        onDutyPension: null,
        fullCheckIn: null,
        fullCheckInPension: null,
        otherPension: null,
        deduckMoney: null,
        acutalSalary: null
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
    /** 查询工资明细列表 */
    getList() {
      this.loading = true;
      listTeacherSalary(this.queryParams).then(response => {
        this.teacherSalaryList = response.rows;
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
        teacherId: null,
        teacherName: null,
        teacherCheckInStatistciId: null,
        checkInBeginDate: null,
        checkInEndDate: null,
        checkInSumDays: null,
        checkInTimes: null,
        leaveDays: null,
        onDutyTimes: null,
        onDutyPension: null,
        fullCheckIn: null,
        fullCheckInPension: null,
        otherPension: null,
        deduckMoney: null,
        acutalSalary: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工资明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTeacherSalary(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工资明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTeacherSalary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacherSalary(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工资明细编号为"' + ids + '"的数据项？').then(function() {
        return delTeacherSalary(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('payment/teacherSalary/export', {
        ...this.queryParams
      }, `teacherSalary_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
