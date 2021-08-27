const { join, resolve } = require('path');

const isDevelopment = process.env.NODE_ENV !== 'production';

const rootPath = resolve(__dirname, '..');
const sourcePath = join(rootPath, 'src', 'electron');
const distPath = join(rootPath, 'dist', 'electron-main');

module.exports = {
  entry: join(sourcePath, 'main.ts'),
  output: {
    path: distPath,
    filename: '[name].bundle.js'
  },
  target: 'electron-main',
  mode: isDevelopment ? 'development' : 'production',
  module: {
    rules: [
      {
        test: /\.(js|ts)$/,
        exclude: /node_modules/,
        use: ['babel-loader']
      }
    ]
  },
  node: {
    __dirname: false
  }
}
