import { ComponentMethods } from './component-methods';

function formatDate(time) {
    const newDate = new Date(time);
    return newDate.getFullYear() + '-' +
        (newDate.getMonth() + 1) + '-' + newDate.getDate()
        + " " + newDate.getHours() + '时';
}

const Main = {
    data() {
        return {
            dialogFormVisible: false,
            form: {
                result: "",
                reason: ""
            },
            formLabelWidth: '120px',
            rules: {
                result: [
                    { required: true, message: '请选择是否同意', trigger: 'change' }
                ],
                reason: [
                    { required: true, message: '请输入审批意见', trigger: 'blur' }
                ]
            },
            tableData: [/* ... */],
            currentRow: null
        }
    },
    methods: {
        handleCurrentChange(val) {
            this.currentRow = val;
            console.info(val);
            this.dialogFormVisible = true;
        },
        onSubmit(formName) {
            console.log('onSubmit called'); // 添加调试信息
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    const params = new URLSearchParams();
                    params.append("formId", this.currentRow.form_id);
                    params.append("result", this.form.result);
                    params.append("reason", this.form.reason);
                    params.append("eid", localStorage.getItem("eid"));
                    axios.post("/api/leave/audit", params).then((response) => {
                        const json = response.data;
                        if (json.code === "0") {
                            this.$alert('请假已审批完毕', {
                                callback: function () {
                                    window.location.href = "/notice.html";
                                }
                            });
                        } else {
                            this.$message.error({message: json.message, offset: 100});
                        }
                    }).catch((error) => {
                        console.error(error);
                        this.$message.error({message: '请求失败', offset: 100});
                    });
                }
            });
        }
    },
    mounted() {
        const $message = this.$message;
        axios.get("/api/leave/list?eid=" + localStorage.getItem("eid"))
            .then((response) => {
                const json = response.data;
                if (json.code === '0') {
                    this.tableData.splice(0, this.tableData.length);
                    const formList = json.data.list;
                    formList.forEach((item) => {
                        switch (item.form_type) {
                            case 1:
                                item.ftype = "事假";
                                break;
                            case 2:
                                item.ftype = "病假";
                                break;
                            case 3:
                                item.ftype = "工伤假";
                                break;
                            case 4:
                                item.ftype = "婚假";
                                break;
                            case 5:
                                item.ftype = "产假";
                                break;
                            case 6:
                                item.ftype = "丧假";
                                break;
                        }
                        item.stime = formatDate(item.start_time);
                        item.etime = formatDate(item.end_time);
                        item.ctime = formatDate(item.create_time);
                        this.tableData.push(item);
                    });
                } else {
                    $message.error({message: json.message, offset: 100});
                }
            }).catch((error) => {
            console.error(error);
            $message.error({message: '请求失败', offset: 100});
        });
    }
};

console.log('Main component defined'); // 添加调试信息
const app = Vue.createApp(Main);
console.log('Vue app created'); // 添加调试信息
app.use(ElementPlus);
console.log('ElementPlus used'); // 添加调试信息
app.mount("#app");
console.log('App mounted to #app'); // 添加调试信息
