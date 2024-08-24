<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="studentBillTemplateQueryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模板名称" prop="costTemplateName">
        <el-input
          v-model="queryParams.costTemplateName"
          placeholder="请输入模板名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="适用年级" prop="costUseGrade">
        <el-select v-model="queryParams.costUseGrade" placeholder="请选择适用年级"
                   @change="handleQuery">
          <el-option
            v-for="item in grades"
            :key="item.id"
            :label="item.grade"
            :value="item.grade">
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

    <el-table v-loading="loading" :data="studentBillTemplateList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="costTemplateId" />
      <el-table-column label="模板名称" align="center" prop="costTemplateName" />
      <el-table-column label=" 日收费" align="center" prop="costFeePerDay">
        <template slot-scope="scope">
          <i v-if="1===scope.row.costUseFeePerDay" class="el-icon-star-on"></i>{{scope.row.costFeePerDay}}
        </template>
      </el-table-column>
      <!--el-table-column label="使用日收费" align="center" prop="costUseFeePerDay" /-->
      <el-table-column label="考勤周期收费" align="center" prop="costFeePerMonth">
        <template slot-scope="scope">
          <i v-if="1===scope.row.costUseFeePerMonth" class="el-icon-star-on"></i>{{scope.row.costFeePerMonth}}
        </template>
      </el-table-column>
      <!--el-table-column label="使用每月收费" align="center" prop="costUseFeePerMonth" /-->
      <!-- el-table-column label="课时费" align="center" prop="costFeePerLesson">
        <template slot-scope="scope">
          <i v-if="1===scope.row.costUseFeePerLesson" class="el-icon-star-on"></i>{{scope.row.costFeePerLesson}}
        </template>
      </el-table-column -->
      <!--el-table-column label="使用课时费" align="center" prop="costUseFeePerLesson" /-->
      <el-table-column label="伙食费" align="center" prop="costFoodFee" />
      <el-table-column label="材料费" align="center" prop="costTextbooksFee" />
      <el-table-column label="适用年级" align="center" prop="costUseGrade" />
      <!--el-table-column label="默认" align="center" prop="costDefault" /-->
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
    <el-dialog :title="title" :visible.sync="open" center width="1000px" :closeOnClickModal="false" append-to-body>
      <el-form ref="studentBillTemplateForm" :model="form" :rules="rules" label-width="170px">
        <el-row :gutter="20">
          <el-col :span="18">
            <el-form-item label="模板名称：" prop="costTemplateName">
              <el-input v-model="form.costTemplateName" placeholder="请输入模板名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="日收费（元）：" prop="costFeePerDay">
              <el-input v-model="form.costFeePerDay" @input="form.costFeePerDay = form.costFeePerDay.replace(/[^\d.]/g,'')" placeholder="请输入每天费用" @blur="verifyFeeUse(1)"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="使用日收费：" prop="costUseFeePerDay">
              <template>
                <el-radio-group v-model="form.costUseFeePerDay" size="small" @change="verifyFeeUse(1)">
                  <el-radio :label="1">使用</el-radio>&nbsp;&nbsp;&nbsp;
                  <el-radio :label="0">不使用</el-radio>
                </el-radio-group>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="考勤周期收费（元）：" prop="costFeePerMonth">
              <el-input v-model="form.costFeePerMonth" @input="form.costFeePerMonth = form.costFeePerMonth.replace(/[^\d.]/g,'')" placeholder="请输入每月收费" @blur="verifyFeeUse(2)"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="使用考勤周期收费：" prop="costUseFeePerMonth">
              <template>
                <el-radio-group v-model="form.costUseFeePerMonth" size="small" @change="verifyFeeUse(2)">
                  <el-radio :label="1">使用</el-radio>
                  <el-radio :label="0">不使用</el-radio>
                </el-radio-group>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
        <!-- el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="课时费（元）：" prop="costFeePerLesson">
              <el-input v-model="form.costFeePerLesson" @input="form.costFeePerLesson = form.costFeePerLesson.replace(/[^\d.]/g,'')" placeholder="请输入课时费" @blur="verifyFeeUse(3)"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="使用课时费：" prop="costUseFeePerLesson">
              <template>
                <el-radio-group v-model="form.costUseFeePerLesson" size="small" @change="verifyFeeUse(3)">
                  <el-radio :label="1">使用</el-radio>
                  <el-radio :label="0">不使用</el-radio>
                </el-radio-group>
              </template>
            </el-form-item>
          </el-col>
        </el-row -->
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="伙食费（元）：" prop="costFoodFee">
              <el-input v-model="form.costFoodFee" @input="form.costFoodFee = form.costFoodFee.replace(/[^\d.]/g,'')" placeholder="请输入伙食费"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="材料费（元）：" prop="costTextbooksFee">
              <el-input v-model="form.costTextbooksFee" @input="form.costTextbooksFee = form.costTextbooksFee.replace(/[^\d.]/g,'')" placeholder="请输入材料费"><template slot="append">元</template></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="适合哪个年级用：" prop="costUseGrade">
          <el-select v-model="form.costUseGrade" placeholder="请选择适用年级"
                     @change="handleQuery">
            <el-option
              v-for="item in grades"
              :key="item.id"
              :label="item.grade"
              :value="item.grade">
            </el-option>
          </el-select>
        </el-form-item>
        <!--el-form-item-- label="默认" prop="costDefault">
          <el-input v-model="form.costDefault" placeholder="请输入默认" />
        </el-form-item-->
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
import { listStudentBillTemplate, getStudentBillTemplate, delStudentBillTemplate, addStudentBillTemplate, updateStudentBillTemplate } from "@/api/bill/studentBillTemplate";
import { getGrades } from '@/api/info/studentInfo'

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
        costTemplateName: [
          { required: true, message: "模板名称不能为空", trigger: "blur" }
        ],
        costUseGrade: [
          { required: true, message: "适用年级不能为空", trigger: "blur" }
        ],
        costFeePerDay: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)|0/, message: "只能输入最多带两位小数的金额"}],
        costFeePerMonth: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)|0/, message: "只能输入最多带两位小数的金额"}],
        costFeePerLesson: [{pattern: /^([1-9][0-9]*)+(\.[0-9]{1,2})?$|(^0\.[0-9]{1,2}?$)|0/, message: "只能输入最多带两位小数的金额"}],
      },
      grades:[],
    };
  },
  created() {
    this.getList();
    this.initGrades();
  },
  methods: {
    verifyFeeUse(index){
      if (1 === index){
        null !== this.form.costFeePerDay && '' !== this.form.costFeePerDay ? this.changeUseAble(1,0,0) : this.changeUseAble(0,0,0);
      } else if (2 === index){
        null !== this.form.costFeePerMonth && '' !== this.form.costFeePerMonth ? this.changeUseAble(0,1,0) : this.changeUseAble(0,0,0);
      } else {
        null !== this.form.costFeePerLesson && '' !== this.form.costFeePerLesson ? this.changeUseAble(0,0,1) : this.changeUseAble(0,0,0);
      }
    },
    changeUseAble(useDay,useMonth,useLesson){
      this.form.costUseFeePerDay = useDay;
      this.form.costUseFeePerMonth = useMonth;
      this.form.costUseFeePerLesson = useLesson;
    },
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
      this.resetForm("studentBillTemplateForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("studentBillTemplateQueryForm");
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
      this.$refs["studentBillTemplateForm"].validate(valid => {
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
      }, `学生费用模板_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
