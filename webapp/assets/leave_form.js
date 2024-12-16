import{ ComponentMethods } from './component-methods'

import ElementPlus from 'element-plus';


const Main = {
    data() {
        return {
            employee: {},
            department: {},
            form: {
                formType: "",
                timeRange: "",
                startTime: "",
                endTime: "",
                reason: "",
                eid: ""
            },
            rules: {
                timeRange: [
                    { required: true, message: '请选择请假时间', trigger: 'blur' }
                ],
                reason: [
                    { required: true, message: '请填写请假原因', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        changeTimeRange() {
            this.form.startTime = this.form.timeRange[0].getTime();
            this.form.endTime = this.form.timeRange[1].getTime();
            console.log("Start Time:", this.form.startTime);
            console.log("End Time:", this.form.endTime);
        },
        onSubmit(formName) {
            console.log('onSubmit called with formName:', formName); // 添加日志输出
            const $message = this.$message;
            const formData = this.form;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    const currentTime = new Date().getTime();
                    const oneYearLater = currentTime + 365 * 24 * 60 * 60 * 1000;

                    if (this.form.startTime < 0 || this.form.startTime > oneYearLater) {
                        $message.error({ message: '开始时间必须在当前时间和未来一年之间', offset: 100 });
                        return;
                    }

                    if (this.form.endTime < 0 || this.form.endTime > oneYearLater) {
                        $message.error({ message: '结束时间必须在当前时间和未来一年之间', offset: 100 });
                        return;
                    }

                    if (this.form.startTime >= this.form.endTime) {
                        $message.error({ message: '开始时间必须早于结束时间', offset: 100 });
                        return;
                    }

                    console.log("Form Data:", formData);

                    const params = new URLSearchParams();
                    params.append("formType", formData.formType);
                    params.append("startTime", formData.startTime);
                    params.append("endTime", formData.endTime);
                    params.append("reason", formData.reason);
                    params.append("eid", localStorage.getItem("eid"));

                    axios.post("/api/leave/create", params).then(response => {
                        const json = response.data;
                        console.log("Response:", json);
                        if (json.code === '0') {
                            this.$alert("请假单已上交，等待上级审批", {
                                callback: function () {
                                    window.location.href = "/notice.html"
                                }
                            })
                        } else {
                            $message.error({ message: json.message, offset: 100 })
                        }
                    }).catch(error => {
                        console.error("Error:", error);
                        $message.error({ message: '请求失败，请稍后再试', offset: 100 });
                    });
                }
            })
        }
    },
    mounted() {
        console.log('Component mounted'); // 添加日志输出
        this.employee = JSON.parse(localStorage.getItem("employee"));
        this.department = JSON.parse(localStorage.getItem("department"));
    }
};

const app = Vue.createApp(Main);
app.use(ElementPlus, { locale: ElementPlusLocaleZhCn });
app.mount('#app');