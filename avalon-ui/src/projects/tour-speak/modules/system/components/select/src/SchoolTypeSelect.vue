<template>
    <el-select v-model="selectValue" :size="size" @change="handleChangeSelectValue" :clearable="clearable"
               placeholder="请选择学校类型">
        <el-option v-if="showAllOption" :label="allOptionLabel" :value="allOptionValue"></el-option>
        <el-option v-for="(row, index) in selectRows"
                   :key="index"
                   :label="row.name"
                   :value="`${row.id}`">
        </el-option>
    </el-select>
</template>

<script>

    import Select from '../../../../../../../assets/template/mixins/select.js';
    import {Data as GlobalData} from "../../../../../mixins/global.js";
    import PubOrgUrls from "../../../../../urls/pub-org-urls";

    export default {

        name: "school-type-select",

        mixins: [Select],

        props: {
            size: {
                type: String,
                default: 'small'
            },
            clearable: {
                type: Boolean,
                default: true
            }
        },

        methods: {
            getSelectRows() {
                this.$Ajax.get(PubOrgUrls.get.listSchoolType)
                    .success(true, data => {
                        this.selectRows = data.rows;
                        if (this.initSelectedFirst && this.selectRows.length > 0) {
                            this.selectValue = this.selectRows[0].value;
                        }
                    });
            }
        },

        created() {
            this.getSelectRows();
        }

    }
</script>
