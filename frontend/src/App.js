import React from 'react';
import './App.css';
import Button from "@material-ui/core/Button/Button";
import Chip from "@material-ui/core/Chip/Chip";
import Avatar from "@material-ui/core/Avatar/Avatar";
import List from "@material-ui/core/List/List";
import {DragDropContext, Draggable, Droppable} from "react-beautiful-dnd";

const dataMode = {
  SINGLE: 'single',
  MULTIPLE: 'multiple'
}

const reorder = (list, startIndex, endIndex) => {
  const result = Array.from(list);
  const [removed] = result.splice(startIndex, 1);
  result.splice(endIndex, 0, removed);

  return result;
};


class App extends React.Component {

  constructor (props) {
    super(props)

    this.openDialog = this.openDialog.bind(this);

    this.ui = props.ui;

    this.state = {items: []};

    this.extensionConfig = JSON.parse(this.ui.extension.config);
    this.application = this.extensionConfig.application;
    if (this.extensionConfig.dataMode === dataMode.SINGLE) {
      this.dataMode = dataMode.SINGLE;
    } else {
      this.dataMode = dataMode.MULTIPLE;
    }

    this.onDragEnd = this.onDragEnd.bind(this);
  }

  onDragEnd (result) {
    // dropped outside the list
    if (!result.destination) {
      return;
    }

    const items = reorder(
      this.state.items,
      result.source.index,
      result.destination.index
    );

    this.setState({
      items
    });
    this.ui.document.field.setValue(JSON.stringify(items));
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
    const items = this.state.items.filter(value => value.id !== itemToDelete.id);
    this.setState({items: items});
    this.ui.document.field.setValue(JSON.stringify(items));
  };

  async openDialog () {
    try {
      const brDocument = await this.ui.document.get();
      const context = {documentId: brDocument.id, documentLocale: brDocument.locale, userId: this.ui.user.id};
      const value = {items: this.state.items, context: context}
      const extensionConfig = JSON.parse(this.ui.extension.config);
      this.dialogOptions = {
        title: extensionConfig.title,
        url: './dialog',
        size: extensionConfig.size,
        value: JSON.stringify(value)
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

  render () {
    return <div className="App">
      {this.state.mode === 'edit' ?
        <DragDropContext onDragEnd={this.onDragEnd}>
          <Droppable droppableId="droppable">
            {(provided, snapshot) => (
              <List
                {...provided.droppableProps}
                ref={provided.innerRef}>
                {this.state.items.map((item, index) => (
                  <Draggable key={item.id} draggableId={item.id} index={index}>
                    {(provided, snapshot) => (
                      <Chip ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps} key={index}
                            size={'medium'}
                            avatar={<Avatar src={item.image ? item.image : 'default'}></Avatar>}
                            label={item.title}
                            onDelete={this.handleDelete(item)}/>
                    )}
                  </Draggable>
                ))}
                {provided.placeholder}
              </List>
            )}
          </Droppable>
        </DragDropContext>
        :
        <List>
          {this.state.items.map((p, id) =>
            <Chip key={id}
                  size={'medium'}
                  avatar={<Avatar src={p.image ? p.image : 'default'}></Avatar>}
                  label={p.title}/>
          )}
        </List>
      }
      <Button disabled={(this.state.mode !== 'edit')} onClick={this.openDialog}>Open Browser</Button>
    </div>
  }
}

export default App;
