import employeeApi from '../api/employees'

export default {
  state: {
  },
  actions: {
    async fetchEmployeesAction({commit, state}) {
      const response = await employeeApi.page(state.currentPage)
      const data = await response.json()

      return data
    }
  },
  getters: {
  },
  mutations: {
    
  }
}
