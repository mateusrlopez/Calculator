import { app, BrowserWindow, Menu } from 'electron';
import { resolve } from 'path';
import { format } from 'url';

const isDevelopment = process.env.NODE_ENV !== 'production';

function createWindow() {
  let window = new BrowserWindow({
    width: 420,
    height: 630,
    resizable: false,
    webPreferences: {
      nodeIntegration: true,
      contextIsolation: false,
    },
  });

  const viewURL = isDevelopment
    ? format({ pathname: 'localhost:4000', protocol: 'http:', slashes: true })
    : format({
        pathname: resolve(__dirname, '..', '..', 'dist', 'electron-renderer', 'index.html'),
        protocol: 'file:',
        slashes: true,
      });

  window.loadURL(viewURL);

  window.on('closed', () => {
    window = null;
  });
}

app.on('ready', () => {
  createWindow();
  Menu.setApplicationMenu(null);
});
