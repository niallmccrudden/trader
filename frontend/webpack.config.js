const webpack = require('webpack');
const path = require('path');

const BUILD_DIR = path.resolve(__dirname, 'public');
const BUILD_FILE_NAME = 'bundle.js'
const APP_DIR = path.resolve(__dirname, 'client');

const config = {
  // entry: APP_DIR + '/index.jsx',
  entry: [
      // 'webpack-dev-server/client?http://0.0.0.0:8080', // WebpackDevServer host and port
      // 'webpack/hot/only-dev-server', // "only" prevents reload on syntax errors
      APP_DIR + '/index.jsx' // Your appʼs entry point
  ],
  devtool: 'source-map',
  output: {
    path: path.resolve(__dirname, BUILD_DIR),
    filename: BUILD_FILE_NAME,
    publicPath: 'http://localhost:8080/'
  },
  module: {
	  loaders : [
	      {
	        test : /\.jsx?/,
	        include : APP_DIR,
	        loaders : ['babel-loader']
	      }
	   ]
   },
   devServer: {
        headers: { "Access-Control-Allow-Origin": "*" }
   },
   plugins: [
       // new webpack.NoErrorsPlugin(),
       // not needed if --hot in command line - new webpack.HotModuleReplacementPlugin()
   ]
};

module.exports = config;