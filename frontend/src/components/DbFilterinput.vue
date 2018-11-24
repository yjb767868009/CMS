<template>
    <el-form :inline="true" :model="formInline">

        <!-- <el-form-item label="">
            <el-select v-model="formInline.sex" clearable placeholder="number or name"
                       v-on:visible-change="selectDemo">
                <el-option
                        v-for="(item, index) in type_options"
                        :key = "index"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </el-form-item> -->
        <el-form-item>
        <el-input
            placeholder= "number or name"
            clearable
            prefix-icon="el-icon-search"
            v-model="formInline.filter"
        >
        </el-input>
        </el-form-item>
        
        <el-form-item label="每页显示">
            <el-select v-model="formInline.itemsPerPage"></el-select>
        </el-form-item>

        <el-form-item>
            <el-button>搜索</el-button>
        </el-form-item>
        
        <el-form-item>
            <el-button type="primary" class="el-icon-plus"> 新增教师账号</el-button>
        </el-form-item>
        
        <!-- <el-form-item v-if='formInline.sex' label="Description">
            <el-input v-model="formInline.email" placeholder="Please input suffix of email"></el-input>
        </el-form-item>

        <el-form-item v-else='formInline.sex' label="Description">
            <el-input v-model="formInline.email" disabled placeholder="Please input suffix of email"></el-input>
        </el-form-item> -->



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
                    sex: '',
                    email: ''
                },
                formLabelWidth: '120px'
            }
        },

        watch: {
            'formInline.sex': 'filterResultData',
            'formInline.email': 'filterResultData'
        },

        methods: {
            selectDemo: function (params) {
                if (params) {
                    this.$axios.get("http://127.0.0.1:8000/api/persons/sex")
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
                    this.$axios.get("http://127.0.0.1:8000/api/persons", {
                        params: {
                            sex: this.formInline.sex,
                            email: this.formInline.email,
                        }
                    }).then((response) => {
                        response.data['sex'] = this.formInline.sex;
                        response.data['email'] = this.formInline.email;
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
