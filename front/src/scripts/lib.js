export default {

  /* 1000 단위마다 ,  표시 */
  addComma(num) {
    var regexp = /\B(?=(\d{3})+(?!\d))/g;
    return num.toString().replace(regexp, ',');
  }

}