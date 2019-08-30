import React, {Fragment} from 'react';
import './App.css';
import Button from "@material-ui/core/Button/Button";
import {SortableContainer, SortableElement} from "react-sortable-hoc";
import arrayMove from "array-move";
import List from "@material-ui/core/List/List";
import Fab from "@material-ui/core/Fab/Fab";
import DeleteIcon from "@material-ui/core/SvgIcon/SvgIcon";

const dataMode = {
  SINGLE: 'single',
  MULTIPLE: 'multiple'
}

const GalleryItem = ({photo, margin, direction, top, left}) => {
  const imgStyle = {margin: margin};
  if (direction === "column") {
    imgStyle.position = "absolute";
    imgStyle.left = left;
    imgStyle.top = top;
  }

  return (
    <Fragment>
      <img
        style={imgStyle}
        src={photo.image}
        width={100}
        alt={photo.title}
      />

    </Fragment>
  );
};

const SortableItem = SortableElement(item => <Fragment>
  {console.log(item)}
  <img
    // style={imgStyle}

    src={item.value.image}
    width={100}
    alt={item.value.title}
  />
</Fragment>);

const SortableList = SortableContainer(({items}) => (
  <List>
    {items.map((item, index) =>
      <SortableItem key={`item-${index}`}
                    index={index}
                    sortIndex={index}
                    value={item}/>
    )}
  </List>
  // <Gallery photos={items} renderImage={props => <SortableItem  {...props} />}/>
));

class App extends React.Component {

  constructor (props) {
    super(props)

    this.openDialog = this.openDialog.bind(this);
    this.onSortEnd = this.onSortEnd.bind(this);

    this.ui = props.ui;

    this.state = {items: []};

    this.extensionConfig = JSON.parse(this.ui.extension.config);
    this.application = this.extensionConfig.application;
    if (this.extensionConfig.dataMode === dataMode.SINGLE) {
      this.dataMode = dataMode.SINGLE;
    } else {
      this.dataMode = dataMode.MULTIPLE;
    }

  }

  componentDidMount () {
    this.setInitialState(this.ui);
  }

  async setInitialState (ui) {
    try {
      const brDocument = await ui.document.get();
      this.mode = brDocument.mode;
      this.setState({mode: this.mode});

      const value = await ui.document.field.getValue();
      let items = JSON.parse(value);

      if (this.mode === dataMode.SINGLE && !Array.isArray(items)) {
        items = [];
        items.push(items)
      }

      this.setState({items: items});
    } catch (error) {
      console.error('Failed to register extension:', error.message);
      console.error('- error code:', error.code);
    }
  }

  handleDelete = itemToDelete => () => {
    console.log('delete mofo')
    const items = this.state.items.filter(value => value.id !== itemToDelete.id);
    this.setState({items: items});
    this.ui.document.field.setValue(JSON.stringify(items));
  };

  async openDialog () {
    try {
      const extensionConfig = JSON.parse(this.ui.extension.config);
      this.dialogOptions = {
        title: extensionConfig.title,
        url: './dialog',
        size: extensionConfig.size,
        value: JSON.stringify(this.state.items)
      };

      const response = await this.ui.dialog.open(this.dialogOptions);
      if (this.mode === dataMode.SINGLE) {
        const items = [];
        await this.ui.document.field.setValue(JSON.stringify(items));
      } else {
        this.setState({items: response});
        const items = JSON.stringify(response);
        await this.ui.document.field.setValue(items);
      }

    } catch (error) {
      if (error.code === 'DialogCanceled') {
        return;
      }
      console.error('Error after open dialog: ', error.code, error.message);
    }

  }

  onSortEnd (collection) {
    this.setState(({items}) => ({
      items: arrayMove(items, collection.oldIndex, collection.newIndex),
    }));
    this.sanitizeItems();
    this.ui.document.field.setValue(JSON.stringify(this.state.items));
  }

  sanitizeItems () {
    this.state.items.forEach(value => {
      delete value.width;
      delete value.height;
    })
  }

  render () {
    return <div className="App">
      <SortableList handleDelete={this.handleDelete} items={this.state.items} onSortEnd={this.onSortEnd} axis={"xy"}/>
      <Button disabled={(this.state.mode !== 'edit')} onClick={this.openDialog}>Open Browser</Button>
    </div>
  }
}

export default App;
