<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="studentBillQueryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生id" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考勤统计表id" prop="checkInStatisticId">
        <el-input
          v-model="queryParams.checkInStatisticId"
          placeholder="请输入考勤统计表id"
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
      <el-form-item label="缴费方式" prop="billMode">
        <el-select v-model="queryParams.billMode" placeholder="请选择缴费方式" @change="handleQuery">
          <el-option
            v-for="item in options"
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
      <!-- el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['payment:studentBill:add']"
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
          v-hasPermi="['payment:studentBill:edit']"
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
          v-hasPermi="['payment:studentBill:remove']"
        >删除</el-button>
      </el-col -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['payment:studentBill:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentBillList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" /><el-table-column label="操作" align="center" class-name="small-padding fixed-width">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="text"
          icon="el-icon-edit"
          @click="handleUpdate(scope.row)"
          v-hasPermi="['payment:studentBill:edit']"
        >修改</el-button>
        <!-- el-button
          size="mini"
          type="text"
          icon="el-icon-delete"
          @click="handleDelete(scope.row)"
          v-hasPermi="['payment:studentBill:remove']"
        >删除</el-button -->
      </template>
    </el-table-column>
      <!-- el-table-column label="id" align="center" prop="id" / -->
      <el-table-column label="学生id" align="center" prop="studentId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="考勤统计表id" align="center" prop="checkInStatisticId" />
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
      <el-table-column label="考勤次数" align="center" prop="checkInTimes" />
      <el-table-column label="请假次数" align="center" prop="leaveTimes" />
      <el-table-column label="视同考勤次数" align="center" prop="countAsCheckInTimes" />
      <el-table-column label="每月收费" align="center" prop="perMonthFee" />
      <el-table-column label="每天收费" align="center" prop="perDayFee" />
      <el-table-column label="资料费" align="center" prop="textbookFee" />
      <el-table-column label="伙食费" align="center" prop="foodFee" />
      <el-table-column label="账单费用" align="center" prop="billFee" />
      <el-table-column label="预收费" align="center" prop="advanceFee" />
      <el-table-column label="优惠金额" align="center" prop="coupon" />
      <el-table-column label="实际每月收费" align="center" prop="actualPerMonthFee" />
      <el-table-column label="实际每日收费" align="center" prop="actualPerDayFee" />
      <el-table-column label="实际资料费" align="center" prop="actualTextbookFee" />
      <el-table-column label="实际伙食费" align="center" prop="actualFoodFee" />
      <el-table-column label="实际账单费用" align="center" prop="acutalBillFee" />
      <el-table-column label="缴费状态" align="center" prop="billStatus" />
      <el-table-column label="缴费方式" align="center" prop="billMode">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.billMode === 0">微信</el-tag><el-tag type="success" v-else-if="scope.row.billStatus === 1">支付宝</el-tag><el-tag type="warning" v-else-if="scope.row.billStatus === 2">现金</el-tag><el-tag type="info" v-else>其他</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="缴费证明" align="center" prop="billEvidence" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改学生账单明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="studentBillForm" :model="form" :rules="rules" label-width="80px">
        <!-- el-form-item label="学生id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生id" />
        </el-form-item -->
        <el-form-item label="学生姓名" prop="studentName">
          <el-input disabled v-model="form.studentName" placeholder="请输入学生姓名" />
        </el-form-item>
        <!-- el-form-item label="考勤统计表id" prop="checkInStatisticId">
          <el-input v-model="form.checkInStatisticId" placeholder="请输入考勤统计表id" />
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
        </el-form-item -->
        <el-form-item label="考勤次数" prop="checkInTimes">
          <el-input disabled v-model="form.checkInTimes" placeholder="请输入考勤次数" />
        </el-form-item>
        <el-form-item label="请假次数" prop="leaveTimes">
          <el-input disabled v-model="form.leaveTimes" placeholder="请输入请假次数" />
        </el-form-item>
        <!-- el-form-item label="视同考勤次数" prop="countAsCheckInTimes">
          <el-input v-model="form.countAsCheckInTimes" placeholder="请输入视同考勤次数" />
        </el-form-item>
        <el-form-item label="每月收费" prop="perMonthFee">
          <el-input v-model="form.perMonthFee" placeholder="请输入每月收费" />
        </el-form-item>
        <el-form-item label="每天收费" prop="perDayFee">
          <el-input v-model="form.perDayFee" placeholder="请输入每天收费" />
        </el-form-item>
        <el-form-item label="资料费" prop="textbookFee">
          <el-input v-model="form.textbookFee" placeholder="请输入资料费" />
        </el-form-item>
        <el-form-item label="伙食费" prop="foodFee">
          <el-input v-model="form.foodFee" placeholder="请输入伙食费" />
        </el-form-item>
        <el-form-item label="账单费用" prop="billFee">
          <el-input v-model="form.billFee" placeholder="请输入账单费用" />
        </el-form-item>
        <el-form-item label="预收费" prop="advanceFee">
          <el-input v-model="form.advanceFee" placeholder="请输入预收费" />
        </el-form-item -->
        <el-form-item label="优惠金额" prop="coupon">
          <el-input v-model="form.coupon" placeholder="请输入优惠金额" />
        </el-form-item>
        <!-- el-form-item label="实际每月收费" prop="actualPerMonthFee">
          <el-input v-model="form.actualPerMonthFee" placeholder="请输入实际每月收费" />
        </el-form-item>
        <el-form-item label="实际每日收费" prop="actualPerDayFee">
          <el-input v-model="form.actualPerDayFee" placeholder="请输入实际每日收费" />
        </el-form-item>
        <el-form-item label="实际资料费" prop="actualTextbookFee">
          <el-input v-model="form.actualTextbookFee" placeholder="请输入实际资料费" />
        </el-form-item>
        <el-form-item label="实际伙食费" prop="actualFoodFee">
          <el-input v-model="form.actualFoodFee" placeholder="请输入实际伙食费" />
        </el-form-item -->
        <el-form-item label="实际账单费用" prop="acutalBillFee">
          <el-input v-model="form.acutalBillFee" placeholder="请输入实际账单费用" />
        </el-form-item>
        <el-form-item label="缴费方式" prop="billMode">
          <el-select v-model="form.billMode" placeholder="请选择缴费方式" @change="handleQuery">
            <el-option
              v-for="item in options"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- el-form-item label="缴费证明" prop="billEvidence">
          <el-input v-model="form.billEvidence" placeholder="请输入缴费证明" />
        </el-form-item -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listStudentBill, getStudentBill, delStudentBill, addStudentBill, updateStudentBill } from "@/api/payment/studentBill";

  export default {
    name: "StudentBill",
    data() {
      return {
        options :[{label:"微信", value: 0},{label:"支付宝", value: 1},{label:"现金", value: 2},{label:"其他", value: 3}],
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
        // 学生账单明细表格数据
        studentBillList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          studentId: null,
          studentName: null,
          checkInStatisticId: null,
          checkInBeginDate: null,
          checkInEndDate: null,
          checkInTimes: null,
          leaveTimes: null,
          countAsCheckInTimes: null,
          perMonthFee: null,
          perDayFee: null,
          textbookFee: null,
          foodFee: null,
          billFee: null,
          advanceFee: null,
          coupon: null,
          actualPerMonthFee: null,
          actualPerDayFee: null,
          actualTextbookFee: null,
          actualFoodFee: null,
          acutalBillFee: null,
          billStatus: null,
          billMode: null,
          billEvidence: null
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
      /** 查询学生账单明细列表 */
      getList() {
        this.loading = true;
        listStudentBill(this.queryParams).then(response => {
          this.studentBillList = response.rows;
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
          studentId: null,
          studentName: null,
          checkInStatisticId: null,
          checkInBeginDate: null,
          checkInEndDate: null,
          checkInTimes: null,
          leaveTimes: null,
          countAsCheckInTimes: null,
          perMonthFee: null,
          perDayFee: null,
          textbookFee: null,
          foodFee: null,
          billFee: null,
          advanceFee: null,
          coupon: null,
          actualPerMonthFee: null,
          actualPerDayFee: null,
          actualTextbookFee: null,
          actualFoodFee: null,
          acutalBillFee: null,
          billStatus: null,
          billMode: null,
          billEvidence: null
        };
        this.resetForm("studentBillForm");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("studentBillQueryForm");
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
        this.title = "添加学生账单明细";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getStudentBill(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改学生账单明细";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["studentBillForm"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateStudentBill(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addStudentBill(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除学生账单明细编号为"' + ids + '"的数据项？').then(function() {
          return delStudentBill(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('payment/studentBill/export', {
          ...this.queryParams
        }, `studentBill_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
