<template>
    <div v-loading="loading" class="common-h-min-main">
        <h3 class="common-fs">{{row.name}}</h3>
        <div v-html="row.content" class="common-wordwrap common-margin-t-small"></div>
    </div>
</template>

<script>

    import FamTeacherUrls from "../../../urls/fam-teacher-urls.js";
    import {isEmpty} from "../../../../../utils/util.js";

    export default {

        name: "teacher-info",

        props: {
            teacherId: String
        },

        data() {
            return {
                loading: false,
                row: {}
            };
        },

        watch: {
            teacherId: {
                immediate: true,
                handler(val) {
                    this.getRow();
                }
            }
        },

        methods: {
            getRow() {
                if (isEmpty(this.teacherId)) {
                    this.row = {};
                    return;
                }
                this.loading = true;
                this.$Ajax.get(FamTeacherUrls.get.famTeacherByFamTeacherId, [this.teacherId])
                    .success(true, data => {
                        this.row = data.records.famTeacher;
                    })
                    .finally(() => this.loading = false);
            }
        }

    }
</script>