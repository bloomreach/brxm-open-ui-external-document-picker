import React from 'react';
import './Fallback.css';
import logo from './logo.svg';

function Fallback () {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo"/>
        <p>
          This is an <code>Open UI Plugin</code> and should be added in the context of BRX
        </p>
        <ul>
          <li>
            <a
              className="App-link"
              href="https://github.com/bloomreach/brxm-open-ui-external-document-picker"
              target="_blank"
              rel="noopener noreferrer"
            >
              Documentation BrXM External Document Picker (Open UI)
            </a>
          </li>
          <li>
            <a
              className="App-link"
              href="https://documentation.bloomreach.com/library/concepts/open-ui/configure-a-document-field-extension.html"
              target="_blank"
              rel="noopener noreferrer"
            >
              Configure a Document Field Extension in BrXM
            </a>
          </li>

        </ul>

      </header>
    </div>
  );
}

export default Fallback;
