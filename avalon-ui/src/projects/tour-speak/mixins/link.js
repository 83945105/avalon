/*!
 * Title: 跳转
 * Description:
 * author: baichao
 * date: 2018/1/26
 * version: v1.0
 */

export default {

    methods: {
        /*登录模块开始*/
        routerToLoginIndex() {
            this.$router.push({name: 'Index'});
        },
        routerToLoginOptionsLoading() {
            this.$router.push({name: 'OptionsLoading'});
        },
        /*登录模块结束*/

        /*课程管理开始*/
        routerToCourseManage() {
            this.$router.push({name: 'CourseManage'});
        },
        routerToCourseAdd() {
            this.$router.push({name: 'CourseAdd'});
        },
        routerToCourseEdit({courseId}) {
            this.$router.push({name: 'CourseEdit', params: {courseId: courseId}});
        },
        routerToCourseManageDetails({courseId}) {
            this.$router.push({name: 'CourseManageDetails', params: {courseId: courseId}});
        },
        /*课程管理结束*/

        /*课程中心开始*/
        routerToCourseCenter() {
            this.$router.push({name: 'CourseCenter'});
        },
        routerToCourseCenterDetails({courseId}) {
            this.$router.push({name: 'CourseCenterDetails', params: {courseId: courseId}});
        },
        /*课程中心结束*/

        /*我的课程开始*/
        routerToMyCourseDetails({courseId}) {
            this.$router.push({name: 'MyCourseDetails', params: {courseId: courseId}});
        },
        /*我的课程结束*/

        /*H5开始*/
        routerToSignIn({courseId}) {
            this.$router.push({name: 'SignIn', params: {courseId: courseId}});
        },
        routerToCourseInteract({courseId}) {
            this.$router.push({name: 'CourseInteract', params: {courseId: courseId}});
        },
        /*H5结束*/

        pageToModule(pageName) {
            switch (pageName) {
                case 'Login':
                    window.location.href = `/tour-speak/view/login`;
                    break;
                case 'CourseCenter':
                    window.location.href = `/tour-speak/view/course-center`;
                    break;
                case 'CourseManage':
                    window.location.href = `/tour-speak/view/course-manage`;
                    break;
                case 'MyCourse':
                    window.location.href = `/tour-speak/view/my-course`;
                    break;
                case 'PersonalCenter':
                    window.location.href = `/tour-speak/view/personal-center`;
                    break;
                case 'System':
                    window.location.href = `/tour-speak/view/system`;
                    break;
                default:
                    alert('没有该模块');
            }
        }
    }
};
