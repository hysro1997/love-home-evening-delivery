<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="teacherBillTemplateQueryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="工资模板名称" prop="salaryTemplateName">
        <el-input
          v-model="queryParams.salaryTemplateName"
          placeholder="请输入工资模板名称"
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
          v-hasPermi="['bill:teacherBillTemplate:add']"
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
          v-hasPermi="['bill:teacherBillTemplate:edit']"
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
          v-hasPermi="['bill:teacherBillTemplate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:teacherBillTemplate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherBillTemplateList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="salaryId" />
      <el-table-column label="工资模板名称" align="center" prop="salaryTemplateName" />
      <el-table-column label="月工资" align="center" prop="salaryPerMonth">
        <template slot-scope="scope">
          <i v-if="1===scope.row.salaryUsePerMonth" class="el-icon-star-on"></i>{{scope.row.salaryPerMonth}}
        </template>
      </el-table-column>
      <!--el-table-column label="使用月工资" align="center" prop="salaryUsePerMonth" /-->
      <el-table-column label="日工资" align="center" prop="salaryPerDay">
        <template slot-scope="scope">
          <i v-if="1===scope.row.salaryUserPerDay" class="el-icon-star-on"></i>{{scope.row.salaryPerDay}}
        </template>
      </el-table-column>
      <!--el-table-column label="使用日工资" align="center" prop="salaryUserPerDay" /-->
      <el-table-column label="值班费" align="center" prop="salaryOnDuty" />
      <el-table-column label="请假工资" align="center" prop="salaryOffDutyFee" />
      <!--el-table-column label="默认" align="center" prop="salaryDefault" /-->
      <el-table-column label="全勤奖" align="center" prop="salaryAllDuty" />
      <el-table-column label="适用用工类型" align="center" prop="salaryEmployType">
        <template slot-scope="scope">
          {{scope.row.salaryEmployType === 0 ? "兼职" : scope.row.salaryEmployType === 1 ? "全职" : scope.row.salaryEmployType === 2 ? "实习生" : "暑假工"}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:teacherBillTemplate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:teacherBillTemplate:remove']"
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

    <!-- 添加或修改老师工资模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" :closeOnClickModal="false" append-to-body>
      <el-form ref="teacherBillTemplateForm" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="工资模板名称" prop="salaryTemplateName">
          <el-input v-model="form.salaryTemplateName" placeholder="请输入工资模板名称" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="月工资（元）：" prop="salaryPerMonth">
              <el-input v-model="form.salaryPerMonth" placeholder="请输入月工资"  @blur="verifySalaryUse(1)"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="使用月工资：" prop="salaryUsePerMonth">
              <template>
                <el-radio-group v-model="form.salaryUsePerMonth" size="small" @change="verifySalaryUse(1)">
                  <el-radio :label="1">使用</el-radio>&nbsp;&nbsp;&nbsp;
                  <el-radio :label="0">不使用</el-radio>
                </el-radio-group>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="日工资（元）：" prop="salaryPerDay">
              <el-input v-model="form.salaryPerDay" placeholder="请输入日工资"  @blur="verifySalaryUse(2)"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="使用日工资：" prop="salaryUserPerDay">
              <template>
                <el-radio-group v-model="form.salaryUserPerDay" size="small" @change="verifySalaryUse(2)">
                  <el-radio :label="1">使用</el-radio>&nbsp;&nbsp;&nbsp;
                  <el-radio :label="0">不使用</el-radio>
                </el-radio-group>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="值班费" prop="salaryOnDuty">
              <el-input v-model="form.salaryOnDuty" placeholder="请输入值班费"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="全勤奖" prop="salaryAllDuty">
              <el-input v-model="form.salaryAllDuty" placeholder="请输入全勤奖"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="请假扣除" prop="salaryOffDutyFee">
              <el-input v-model="form.salaryOffDutyFee" placeholder="请假扣除"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="适用的用工类型" prop="salaryEmployType">
              <el-select v-model="form.salaryEmployType" placeholder="请选择用工类型">
                <el-option
                  v-for="item in employType"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
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
import { listTeacherBillTemplate, getTeacherBillTemplate, delTeacherBillTemplate, addTeacherBillTemplate, updateTeacherBillTemplate } from "@/api/bill/teacherBillTemplate";

export default {
  name: "TeacherBillTemplate",
  data() {
    return {
      employType: [{label:"兼职",value:"0"},{label:"全职",value:"1"},{label:"实习生",value:"2"},{label:"暑假工",value:"3"}],
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
      // 老师工资模板表格数据
      teacherBillTemplateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        salaryTemplateName: null,
        salaryPerMonth: null,
        salaryUsePerMonth: null,
        salaryPerDay: null,
        salaryUserPerDay: null,
        salaryOnDuty: null,
        salaryOffDutyFee: null,
        salaryDefault: null,
        salaryAllDuty: null,
        salaryEmployType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        salaryTemplateName: [
          { required: true, message: "工资模板名称不能为空", trigger: "blur" }
        ],
        salaryEmployType: [
          { required: true, message: "适用的工作类型不能为空", trigger: "change" }
        ],
        salaryPerMonth: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)/, message: "只能输入最多带两位小数的金额"}],
        salaryPerDay: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)/, message: "只能输入最多带两位小数的金额"}],
        salaryOnDuty: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)/, message: "只能输入最多带两位小数的金额"}],
        salaryAllDuty: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)/, message: "只能输入最多带两位小数的金额"}],
        salaryOffDutyFee: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)/, message: "只能输入最多带两位小数的金额"}],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    verifySalaryUse(index){
      if (2 === index){
        null !== this.form.salaryPerDay && '' !== this.form.salaryPerDay ? this.changeUseAble(0,1) : this.changeUseAble(0,0);
      } else {
        null !== this.form.salaryPerMonth && '' !== this.form.salaryPerMonth ? this.changeUseAble(1,0) : this.changeUseAble(0,0);
      }
    },
    changeUseAble(salaryUsePerMonth,salaryUserPerDay){
      this.form.salaryUsePerMonth = salaryUsePerMonth;
      this.form.salaryUserPerDay = salaryUserPerDay;
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex % 2 === 1) {
        return 'warning-row';
      } else if (rowIndex % 2 === 0) {
        return 'success-row';
      }
      return '';
    },
    /** 查询老师工资模板列表 */
    getList() {
      this.loading = true;
      listTeacherBillTemplate(this.queryParams).then(response => {
        this.teacherBillTemplateList = response.rows;
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
        salaryId: null,
        salaryTemplateName: null,
        salaryPerMonth: null,
        salaryUsePerMonth: null,
        salaryPerDay: null,
        salaryUserPerDay: null,
        salaryOnDuty: null,
        salaryOffDutyFee: null,
        salaryDefault: null,
        salaryAllDuty: null,
        salaryEmployType: null
      };
      this.resetForm("teacherBillTemplateForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("teacherBillTemplateQueryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.salaryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加老师工资模板";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const salaryId = row.salaryId || this.ids
      getTeacherBillTemplate(salaryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改老师工资模板";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["teacherBillTemplateForm"].validate(valid => {
        if (valid) {
          if (this.form.salaryId != null) {
            updateTeacherBillTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacherBillTemplate(this.form).then(response => {
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
      const salaryIds = row.salaryId || this.ids;
      this.$modal.confirm('是否确认删除老师工资模板编号为"' + salaryIds + '"的数据项？').then(function() {
        return delTeacherBillTemplate(salaryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/teacherBillTemplate/export', {
        ...this.queryParams
      }, `工资模板${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
