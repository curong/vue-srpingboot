module.exports = {
  devServer: {
    proxy: {
      '/api':{
        "target":'http://localhost:4010',
        "pathRewrite":{'^/':''},
        // "changeOrigin":true,
        // // "secure":false,
        // 'ws': true,
      }
    }
  }
}