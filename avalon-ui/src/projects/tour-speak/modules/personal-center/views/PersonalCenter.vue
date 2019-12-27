<template>
    <div class="common-bg-default">
        <h1 class="tour-layout-right-title">个人中心</h1>
        <div style="width: 800px; margin: 0 auto; padding: 30px 0;">
            <div ref="editor" v-html="content" contenteditable="true" class="editor"></div>
            <!--<el-input type="textarea"-->
            <!--:rows="15"-->
            <!--placeholder="请输入个人简介"-->
            <!--v-model="content"-->
            <!--resize="none">-->
            <!--</el-input>-->
            <div class="common-margin-t common-tac">
                <we-button :loading="loading" type="primary" style="width: 90px"
                           @click="submit"
                >保存
                </we-button>
                <we-button :disabled="loading" style="width: 90px">重置</we-button>
            </div>
        </div>
    </div>
</template>

<script>

    import Global from '../../../mixins/global.js';
    import FamTeacherUrls from "../../../urls/fam-teacher-urls.js";

    export default {

        name: "personal-center",

        mixins: [Global],

        data() {
            return {
                loading: false,
                content: ''
            }
        },

        methods: {
            getRow() {
                this.loading = true;
                this.$Ajax.get(FamTeacherUrls.get.currentFamTeacher)
                    .success(true, data => {
                        this.content = data.records.famTeacher.content;
                    })
                    .finally(() => this.loading = false);
            },
            submit() {
                this.loading = true;
                this.$Ajax.put(FamTeacherUrls.put.currentFamTeacher, {content: this.$refs.editor.innerHTML})
                    .success('保存成功')
                    .finally(() => this.loading = false);
            }
        },

        created() {
            this.getRow();
        }
    }
</script>

<style type="text/css" scoped>
    .editor {
        padding: 3px 5px;
        border: 1px solid #DDDDDD;
        height: 300px;
        outline: none;
        display: inline-block;
        width: 800px;
        border-radius: 3px;
    }
</style>
