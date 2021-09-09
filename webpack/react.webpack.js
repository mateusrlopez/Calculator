const { resolve, join } = require('path');
const HTMLWebpackPlugin = require('html-webpack-plugin');

const isDevelopment = process.env.NODE_ENV !== 'production';

const rootPath = resolve(__dirname, '..');
const sourcePath = join(rootPath, 'src', 'react');
const distPath = join(rootPath, 'dist', 'electron-renderer');
const publicPath = join(rootPath, 'public');

module.exports = {
  entry: join(sourcePath, 'index.tsx'),
  output: {
    path: distPath,
    filename: '[name]-[fullhash].bundle.js'
  },
  resolve: {
    extensions: ['.ts', '.js', '.tsx']
  },
  target: 'electron-renderer',
  mode: isDevelopment ? 'development' : 'production',
  devServer: {
    static: {
      directory: distPath,
      publicPath: '/'
    },
    port: 4000,
    hot: true,
    compress: true,
    historyApiFallback: true
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx|ts|tsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader']
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      },
      {
        test: /\.(jpg|jpeg|png|gif|mp3|svg)$/,
        use: ['file-loader']
      }
    ]
  },
  plugins: [
    new HTMLWebpackPlugin({
      template: join(publicPath, 'index.html')
    })
  ]
}
