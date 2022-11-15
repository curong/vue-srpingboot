export default {

  /* 1000 단위마다 ,  표시 */
  addComma(num) {
    const regexp = /\B(?=(\d{3})+(?!\d))/g;
    return num.toString().replace(regexp, ',');
  }

}