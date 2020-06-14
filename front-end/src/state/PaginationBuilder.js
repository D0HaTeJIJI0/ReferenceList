class PaginationBuilder {

  static buildPagination(state) {
    let wrapper = document.getElementById('pagination-wrapper');
    let buttons = "";
    let pageEmployee = state.pageEmployee;
    let minPage = Math.max(pageEmployee.currentPage - Math.floor((state.windowSize - 1) / 2), 1);
    let maxPage = minPage + state.windowSize - 1;
    if (maxPage > pageEmployee.totalPageNumber) {
      maxPage = pageEmployee.totalPageNumber;
      minPage = Math.max(maxPage - state.windowSize + 1, 1)
    }

    if (minPage > 1) {
      buttons += `<button value=1 class="page btn btn-sm btn-info">&#171; First</button>`;
    }
    if (pageEmployee.currentPage > 1) {
      buttons += `<button class="page-backward btn btn-sm btn-info">&#139;</button>`;
    }
    for (let page = minPage; page <= maxPage; page++) {
      if (page == pageEmployee.currentPage) {
        buttons += `<button value=${page} class="page btn btn-sm btn-info active">${page}</button>`;  
      } else {
        buttons += `<button value=${page} class="page btn btn-sm btn-info">${page}</button>`;
      }
    }
    if (pageEmployee.currentPage < pageEmployee.totalPageNumber) {
      buttons += `<button class="page-forward btn btn-sm btn-info">&#155;</button>`;
    }
    if (maxPage < pageEmployee.totalPageNumber) {
      buttons += `<button value=${pageEmployee.totalPageNumber} class="page btn btn-sm btn-info">&#187; Last</button>`;
    }

    wrapper.innerHTML = buttons;

    let backwards = document.getElementsByClassName("page-backward");
    for (let backward of backwards) {
      backward.onclick = () => {
        state.goToPreviousPage();
      }
    }

    let forwards = document.getElementsByClassName("page-forward");
    for (let forward of forwards) {
      forward.onclick = () => {
        state.goToNextPage();
      }
    }

    let pages = document.getElementsByClassName("page");
    for (let page of pages) {
      page.onclick = () => {
        state.goToPage(Number(page.value));
      }
    }
  }

}