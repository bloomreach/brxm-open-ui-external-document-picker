import React from 'react';
import {createRoot} from 'react-dom/client';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import * as UiExtension from '@bloomreach/ui-extension';
import ExtPickerDialog from './Dialog';
import Fallback from './Fallback';

document.addEventListener('DOMContentLoaded', async () => {
  const root = createRoot(document.getElementById('root'));
  try {
    const ui = await UiExtension.register();

    root.render(
      <BrowserRouter>
        <Routes>
          <Route path="/dialog" element={<ExtPickerDialog ui={ui}/>}/>
          <Route path="/" element={<App ui={ui}/>}/>
        </Routes>
      </BrowserRouter>
    );
  } catch (error) {
    console.log(error);
    console.error('Failed to register extension:', error.message);
    console.error('- error code:', error.code);
    root.render(<Fallback/>);
  }
});

serviceWorker.unregister();
