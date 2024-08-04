<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模板名次" prop="costTemplateName">
        <el-input
          v-model="queryParams.costTemplateName"
          placeholder="请输入模板名次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每天费用" prop="costFeePerDay">
        <el-input
          v-model="queryParams.costFeePerDay"
          placeholder="请输入每天费用"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用日收费" prop="costUseFeePerDay">
        <el-input
          v-model="queryParams.costUseFeePerDay"
          placeholder="请输入使用日收费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课时费" prop="costFeePerLesson">
        <el-input
          v-model="queryParams.costFeePerLesson"
          placeholder="请输入课时费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用课时费" prop="costUseFeePerLesson">
        <el-input
          v-model="queryParams.costUseFeePerLesson"
          placeholder="请输入使用课时费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="伙食费" prop="costFoodFee">
        <el-input
          v-model="queryParams.costFoodFee"
          placeholder="请输入伙食费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料费" prop="costTextbooksFee">
        <el-input
          v-model="queryParams.costTextbooksFee"
          placeholder="请输入材料费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每月收费" prop="costFeePerMonth">
        <el-input
          v-model="queryParams.costFeePerMonth"
          placeholder="请输入每月收费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用每月收费" prop="costUseFeePerMonth">
        <el-input
          v-model="queryParams.costUseFeePerMonth"
          placeholder="请输入使用每月收费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="适合哪个年级用" prop="costUseGrade">
        <el-input
          v-model="queryParams.costUseGrade"
          placeholder="请输入适合哪个年级用"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="默认" prop="costDefault">
        <el-input
          v-model="queryParams.costDefault"
          placeholder="请输入默认"
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
          v-hasPermi="['bill:studentBillTemplate:add']"
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
          v-hasPermi="['bill:studentBillTemplate:edit']"
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
          v-hasPermi="['bill:studentBillTemplate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:studentBillTemplate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentBillTemplateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="costTemplateId" />
      <el-table-column label="模板名次" align="center" prop="costTemplateName" />
      <el-table-column label="每天费用" align="center" prop="costFeePerDay" />
      <el-table-column label="使用日收费" align="center" prop="costUseFeePerDay" />
      <el-table-column label="课时费" align="center" prop="costFeePerLesson" />
      <el-table-column label="使用课时费" align="center" prop="costUseFeePerLesson" />
      <el-table-column label="伙食费" align="center" prop="costFoodFee" />
      <el-table-column label="材料费" align="center" prop="costTextbooksFee" />
      <el-table-column label="每月收费" align="center" prop="costFeePerMonth" />
      <el-table-column label="使用每月收费" align="center" prop="costUseFeePerMonth" />
      <el-table-column label="适合哪个年级用" align="center" prop="costUseGrade" />
      <el-table-column label="默认" align="center" prop="costDefault" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:studentBillTemplate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:studentBillTemplate:remove']"
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

    <!-- 添加或修改学生费用模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模板名次" prop="costTemplateName">
          <el-input v-model="form.costTemplateName" placeholder="请输入模板名次" />
        </el-form-item>
        <el-form-item label="每天费用" prop="costFeePerDay">
          <el-input v-model="form.costFeePerDay" placeholder="请输入每天费用" />
        </el-form-item>
        <el-form-item label="使用日收费" prop="costUseFeePerDay">
          <el-input v-model="form.costUseFeePerDay" placeholder="请输入使用日收费" />
        </el-form-item>
        <el-form-item label="课时费" prop="costFeePerLesson">
          <el-input v-model="form.costFeePerLesson" placeholder="请输入课时费" />
        </el-form-item>
        <el-form-item label="使用课时费" prop="costUseFeePerLesson">
          <el-input v-model="form.costUseFeePerLesson" placeholder="请输入使用课时费" />
        </el-form-item>
        <el-form-item label="伙食费" prop="costFoodFee">
          <el-input v-model="form.costFoodFee" placeholder="请输入伙食费" />
        </el-form-item>
        <el-form-item label="材料费" prop="costTextbooksFee">
          <el-input v-model="form.costTextbooksFee" placeholder="请输入材料费" />
        </el-form-item>
        <el-form-item label="每月收费" prop="costFeePerMonth">
          <el-input v-model="form.costFeePerMonth" placeholder="请输入每月收费" />
        </el-form-item>
        <el-form-item label="使用每月收费" prop="costUseFeePerMonth">
          <el-input v-model="form.costUseFeePerMonth" placeholder="请输入使用每月收费" />
        </el-form-item>
        <el-form-item label="适合哪个年级用" prop="costUseGrade">
          <el-input v-model="form.costUseGrade" placeholder="请输入适合哪个年级用" />
        </el-form-item>
        <el-form-item label="默认" prop="costDefault">
          <el-input v-model="form.costDefault" placeholder="请输入默认" />
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
import { listStudentBillTemplate, getStudentBillTemplate, delStudentBillTemplate, addStudentBillTemplate, updateStudentBillTemplate } from "@/api/bill/studentBillTemplate";

export default {
  name: "StudentBillTemplate",
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
      // 学生费用模板表格数据
      studentBillTemplateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        costTemplateName: null,
        costFeePerDay: null,
        costUseFeePerDay: null,
        costFeePerLesson: null,
        costUseFeePerLesson: null,
        costFoodFee: null,
        costTextbooksFee: null,
        costFeePerMonth: null,
        costUseFeePerMonth: null,
        costUseGrade: null,
        costDefault: null
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
    /** 查询学生费用模板列表 */
    getList() {
      this.loading = true;
      listStudentBillTemplate(this.queryParams).then(response => {
        this.studentBillTemplateList = response.rows;
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
        costTemplateId: null,
        costTemplateName: null,
        costFeePerDay: null,
        costUseFeePerDay: null,
        costFeePerLesson: null,
        costUseFeePerLesson: null,
        costFoodFee: null,
        costTextbooksFee: null,
        costFeePerMonth: null,
        costUseFeePerMonth: null,
        costUseGrade: null,
        costDefault: null
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
      this.ids = selection.map(item => item.costTemplateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生费用模板";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const costTemplateId = row.costTemplateId || this.ids
      getStudentBillTemplate(costTemplateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生费用模板";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.costTemplateId != null) {
            updateStudentBillTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudentBillTemplate(this.form).then(response => {
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
      const costTemplateIds = row.costTemplateId || this.ids;
      this.$modal.confirm('是否确认删除学生费用模板编号为"' + costTemplateIds + '"的数据项？').then(function() {
        return delStudentBillTemplate(costTemplateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/studentBillTemplate/export', {
        ...this.queryParams
      }, `studentBillTemplate_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
