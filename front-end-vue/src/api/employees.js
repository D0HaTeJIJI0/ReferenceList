import Vue from 'vue'

export default {
    page: () => Vue.http.get('http://localhost:8081/referenceList/employee/all')
}
