<template>
  <div class="common-full" style="top: 41px;">
    <div v-loading="!skeleton && loading" class="common-full">
      <el-table :data="rows"
                size="medium"
                width="100%"
                height="100%"
                @selection-change="changeRow">
        <template slot="empty">
          <error-page :image-width="180" :image-height="180"></error-page>
        </template>
        <el-table-column type="index"
                         width="50">
        </el-table-column>
        <el-table-column type="selection"
                         width="55">
        </el-table-column>
        <el-table-column prop="date"
                         label="日期"
                         width="70">
        </el-table-column>
        <el-table-column prop="name"
                         label="姓名"
                         min-width="150">
        </el-table-column>
        <el-table-column prop="province"
                         label="省份"
                         width="100"
                         show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="city"
                         label="市区"
                         min-width="185">
        </el-table-column>
        <el-table-column prop="address"
                         label="地址"
                         min-width="185">
        </el-table-column>
        <el-table-column prop="zip"
                         label="邮编"
                         show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status"
                         label="状态"
                         show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" type="success" size="small">启用</el-tag>
            <el-tag v-else-if="scope.row.status === 1" type="danger" size="small">禁用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="operation"
                         fixed="right"
                         label="操作"
                         width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click.native.prevent="editRow(scope.$index, rows)">修改
            </el-button>
            <br/>
            <el-button type="text" size="small" @click.native.prevent="viewRow(scope.$index, rows)">查看
            </el-button>
            <br/>
            <!--<we-popover title="操作" type="confirm"-->
            <!--placement="top-start"-->
            <!--:manual="scope.row.deleteLoading"-->
            <!--confirm-button-text="删了吧"-->
            <!--:cancel-button-text="scope.row.deleteLoading ? '请稍等' : '我再想想'"-->
            <!--:cancel-button-options="{disabled: scope.row.deleteLoading}"-->
            <!--:confirm-button-options="{loading: scope.row.deleteLoading}"-->
            <!--@click-cancel-button="(e, vm) => vm.close()"-->
            <!--@click-confirm-button="(e, vm) => handleDeleteRow(e, vm, scope.$index)">-->
            <!--<template slot="content">-->
            <!--<template v-if="scope.row.deleteLoading">-->
            <!--<span style="color: red">删除中...</span>删除完成后将自动关闭气泡-->
            <!--</template>-->
            <!--<template v-else>-->
            <!--您确定要<span style="color: red">删除</span>该订单吗？-->
            <!--<div style="color: red">删除后不可恢复！！！</div>-->
            <!--</template>-->
            <!--</template>-->
            <!--<el-button type="text" size="small">删除</el-button>-->
            <!--</we-popover>-->
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>

  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
  import merge from "../../../../../../utils/merge.js";

  const Limit = {
    currentPage: 1,
    pageSizes: [10, 20, 50, 100],
    total: 0,
    pageCount: 1
  };

  const Params = {
    typeId: '',
    likeText: '',
    valueMode: '',
    auditRadio: "0"
  };

  export default {
    name: "Home",

    components: {
      ErrorPage
    },

    data() {
      return {
        skeleton: true,
        loading: false,
        limit: merge({pageSize: 20}, Limit),
        params: merge({}, Params),
        rows: []
      }
    },

    methods: {
      reGetRows() {
        merge(this.limit, Limit);
        this.getRows();
      },
      formatterRows(rows) {
        rows.forEach(row => {
          row.deleteLoading = false;
        });
        return rows;
      },
      getRows() {
        this.skeleton = false;
        this.loading = false;
        this.limit = {
          currentPage: 1,
          pageSize: 10,
          total: 100,
          pageCount: 10,
          pageSizes: [50, 100, 150]
        };
        setTimeout(() => {
          let table = [];
          this.loading = false;
          for (let i = 0; i < 1; i++) {
            table.push({
              date: '2016-05-03',
              name: '王小虎',
              province: '上海',
              city: '普陀区',
              address: '上海市普陀区金沙江路 1518 弄',
              zip: 200333,
              status: 0
            });
          }
          this.rows = this.formatterRows(table);
        }, 0);
      },
      changeRow(val) {
      }
    },

    created() {
      this.getRows();
    }
  }
</script>

<style scoped>

</style>
