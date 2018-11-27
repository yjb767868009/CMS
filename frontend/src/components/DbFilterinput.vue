<template>
    <el-form :inline="true" :model="formInline">

        <el-form-item label="">
            <el-input v-model="formInline.number" clearable placeholder="输入教工号"
                       v-on:visible-change="selectDemo">
            </el-input>
        </el-form-item>

        <el-form-item label="每页显示">
            <el-select v-model="formInline.page" placeholder="5">
                <el-option>1</el-option>
                <el-option>2</el-option>
                <el-option>3</el-option>
                <el-option>4</el-option>
                <el-option>5</el-option>
            </el-select>
        </el-form-item>

    </el-form>
</template>

<script>
    import lodash from 'lodash'
    import Bus from '../eventBus'

    export default {
        name: 'db-filterinput',
        data() {
            return {
                type_options: [],
                formInline: {
                    number: '',
                    page: '5'
                },
                formLabelWidth: '120px'
            }
        },

        watch: {
            'formInline.number': 'filterResultData',
            'formInline.page': 'filterResultData'
        },

        methods: {
            selectDemo: function (params) {
                if (params) {
                    this.$axios.get(`http://127.0.0.1:8000/teachers/${this.formInline.number}`)
                        .then((response) => {
                            this.type_options = response.data;
                            console.log(response.data);
                        }).catch(function (response) {
                        console.log(response)
                    });
                }

            },
            filterResultData: _.debounce(
                function () {
                    this.$axios.get(`http://127.0.0.1:8000/teachers/${this.formInline.number}`)
                    .then((response) => {
                        response.data['number'] = this.formInline.number;
                        Bus.$emit('filterResultData', response.data);
                        console.log(response.data);
                    }).catch(function (response) {
                        console.log(response)
                    });

                },
                500
            ),
        }
    }


</script>
