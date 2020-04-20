import React, {Fragment} from 'react';
import TextField from "@material-ui/core/TextField/TextField";
import AppBar from "@material-ui/core/AppBar/AppBar";
import Toolbar from "@material-ui/core/Toolbar/Toolbar";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";
import Dialog from "@material-ui/core/Dialog/Dialog";
import {makeStyles} from "@material-ui/core";
import Chip from "@material-ui/core/Chip/Chip";
import Avatar from "@material-ui/core/Avatar/Avatar";
import Fade from "@material-ui/core/Fade/Fade";
import CircularProgress from "@material-ui/core/CircularProgress/CircularProgress";
import Typography from "@material-ui/core/Typography/Typography";
import Button from "@material-ui/core/Button/Button";
import Fab from "@material-ui/core/Fab/Fab";
import GridList from "@material-ui/core/GridList/GridList";
import GridListTile from "@material-ui/core/GridListTile/GridListTile";
import GridListTileBar from "@material-ui/core/GridListTileBar/GridListTileBar";
import {Check} from "@material-ui/icons";
import Snackbar from "@material-ui/core/Snackbar/Snackbar";
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import FormControl from "@material-ui/core/FormControl/FormControl";
import InputLabel from "@material-ui/core/InputLabel/InputLabel";
import Select from "@material-ui/core/Select/Select";
import MenuItem from "@material-ui/core/MenuItem/MenuItem";

const dataMode = {
  SINGLE: 'single',
  MULTIPLE: 'multiple'
}

const galleryType = {
  GALLERY: 'Gallery',
  LIST: 'List'
}

class ExtPickerDialog extends React.Component {

  constructor (props) {
    super(props)

    this.ui = props.ui;

    this.onScroll = this.onScroll.bind(this);
    this.onOk = this.onOk.bind(this);
    this.changeQuery = this.changeQuery.bind(this);
    this.logError = this.logError.bind(this);
    this.onAggChange = this.onAggChange.bind(this);

    this.extensionConfig = JSON.parse(this.ui.extension.config);
    this.application = this.extensionConfig.application;
    if (this.extensionConfig.dataMode === dataMode.SINGLE) {
      this.dataMode = dataMode.SINGLE;
    } else {
      this.dataMode = dataMode.MULTIPLE;
    }

    if(typeof this.extensionConfig.autoSearch !== 'undefined') {
      this.autoSearch = this.extensionConfig.autoSearch;
    } else {
      this.autoSearch = true;
    }

    this.urlEndpoint = this.ui.baseUrl + 'edp/' + this.application;
    this.state = {
      items: [],
      aggs: [],
      aggValues: {},
      query: this.extensionConfig.query,
      page: 1,
      pageSize: this.extensionConfig.pageSize,
      isLoading: false,
      hasMore: true,
      selectedItems: [],
      open: false,
      galleryType: galleryType.GALLERY
    };

    this.clientId = this.extensionConfig.clientId;

    this.useStyles = makeStyles(theme => ({
      grow: {
        flexGrow: 1
      },
      search: {
        flexGrow: 1,
      }
    }));

    this.extDialog = React.createRef();
  }

  componentDidMount () {
    this.setInitialSelected(this.ui);
  }

  onScroll () {
    const container = this.extDialog.current;
    const scrollY = container.scrollHeight - container.scrollTop;
    const height = container.offsetHeight;
    const offset = height - scrollY;

    if ((offset === 0 || offset === 1) && !this.state.isLoading) {
      this.fetchItems(this.state.query, this.state.page + 1, this.state.pageSize)
        .then(newItems => this.state.items.concat(newItems))
        .then(items => this.setState({items: items, page: this.state.page + 1}));
    }
  }

  async setInitialSelected (ui) {
    try {
      const options = await ui.dialog.options();
      const value = JSON.parse(options.value)
      const items = value.items;
      const context = value.context;
      console.log('init');
      this.setState({context: context});
      this.setState({selectedItems: items});
      this.fetchItems(this.state.query, this.state.page, this.state.pageSize)
        .then(items => this.setState({items: items}));

    } catch (error) {
      console.error('Failed to register extension:', error.message);
      console.error('- error code:', error.code);
    }
  }

  addItem (item) {
    if (this.dataMode === dataMode.SINGLE) {
      const items = [];
      items.push(item);
      this.setState({selectedItems: items});
      this.ui.dialog.close(items);
    } else {
      const items = this.state.selectedItems;
      items.push(item);
      this.setState({selectedItems: items});
    }
  }

  addOrDeleteItem (item) {
    if (this.state.selectedItems.some(e => e.id === item.id)) {
      this.deleteItem(item);
    } else {
      this.addItem(item);
    }
  }

  deleteItem (item) {
    const items = this.state.selectedItems.filter(value => value.id !== item.id);
    this.setState({selectedItems: items});
  }

  alertClose () {
    this.setState({alertOpen: false});
  }

  async fetchItems (query, page, pageSize) {
    this.setState({isLoading: true});
    const url = new URL(this.urlEndpoint + '/search'),
      params = {
        query: query,
        page: page,
        pageSize: pageSize,
        clientId: this.clientId,
        documentId: this.state.context.documentId,
        documentLocale: this.state.context.documentLocale,
        aggs: JSON.stringify(this.state.aggValues)
      }
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key]))
    const fetched =
      await fetch(url)
        .then(response => {
          if (!response.ok) {
            response.text().then(value => this.logError(value))
            return {data:[], aggs:[]};
          }
          return response.json();
        })
        .then(result => {
          this.setState({aggs: result.aggs});
          return result.data;
        })
        .catch(reason => {
          this.logError('Error: ' + reason.toString());
          return [];
        });
    this.setState({isLoading: false});
    return fetched;
  }

  changeQuery (value) {
    this.extDialog.current.scrollTo(0, 0);
    this.setState({query: value, items: [], page: 1});
    this.fetchItems(value, 1, this.state.pageSize)
      .then(items => this.setState({items: items}))
  }

  handleKeyDown (e) {
    if (e.key === 'Enter') {
      this.changeQuery(e.target.value);
    }
  }

  onAggChange (name, values) {
    const aggVal = this.state.aggValues;
    aggVal[name] = values;
    this.setState({aggValues: aggVal, page: 1});
    this.changeQuery(this.state.query);
  }

  onOk () {
    this.ui.dialog.close(this.state.selectedItems);
  }

  isEmpty (val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
  }

  render () {
    const {isLoading} = this.state || false;
    const {aggs} = this.state || [];
    const {alertOpen} = this.state || false;
    const {alertMessage} = this.state || '';
    const {items} = this.state || [];
    const {selectedItems} = this.state || [];
    const classes = this.useStyles;
    const isMultiMode = this.dataMode === dataMode.MULTIPLE;

    return <Dialog fullScreen open={true}>
      <Snackbar
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'center',
        }}
        open={alertOpen}
        message={alertMessage}
        action={
          <React.Fragment>
            <IconButton size="small" aria-label="close" color="inherit" onClick={event => this.alertClose()}>
              <CloseIcon fontSize="small"/>
            </IconButton>
          </React.Fragment>
        }
      />
      <AppBar position="static" color="default">
        <Toolbar>
          <TextField
            className={classes.search}
            autoFocus
            margin="dense"
            id="search"
            style={{
              minWidth: 200
            }}
            label={this.autoSearch ? "Search" : "Search on enter"}
            type="text"
            fullWidth={true}
            onChange={event => this.autoSearch && this.changeQuery(event.target.value)}
            onKeyDown={event => !this.autoSearch && this.handleKeyDown(event)}
          />
          {aggs.map((aggregation, id) =>
            <FormControl key={id} style={{
              minWidth: 120,
            }}>
              {this.isEmpty(this.state.aggValues[aggregation.name]) ?
                <InputLabel>
                  {aggregation.label}
                </InputLabel> : ''
              }
              {(() => {
                switch (aggregation.type) {
                  case "SingleSelect":
                    return <Select value={this.state.aggValues[aggregation.name] !== undefined ? this.state.aggValues[aggregation.name] : this.state.aggValues[aggregation.name] = ''} onChange={event => this.onAggChange(aggregation.name, event.target.value)} style={{height: '50px'}}>
                      <MenuItem value={''}></MenuItem>
                      {aggregation.values.map((value, id) =>
                        <MenuItem key={id} value={value.value}>{value.label}</MenuItem>
                      )}
                    </Select>;
                  case "MultiSelect":
                    return "";
                  default:
                    return "";
                }
              })()}
            </FormControl>
          )}
        </Toolbar>
      </AppBar>
      <DialogContent ref={this.extDialog} onScroll={this.onScroll}>
        <GridList spacing={10} cols={4}>
          {items.map((item, id) =>
            <GridListTile key={id} cols={1} onClick={event => this.addOrDeleteItem(item)} style={{cursor: 'pointer'}}>
              <img src={item.image} alt={item.title}/>
              <GridListTileBar
                title={item.title}
                subtitle={<span>{item.description}</span>}
                actionIcon={(selectedItems.some(e => e.id === item.id)) ?
                  <Fab size={'small'} color="primary" aria-label="Add">
                    <Check/>
                  </Fab> : <Fragment/>
                }
              />
            </GridListTile>
          )}
        </GridList>
      </DialogContent>
      <Fade
        in={isLoading}
        style={{
          zIndex: 9999999, position: 'fixed', bottom: 0, right: 0,
          transitionDelay: isLoading ? '800ms' : '0ms',
        }}
        unmountOnExit>
        <CircularProgress/>
      </Fade>
      {isMultiMode && <Toolbar>
        <Typography variant="h6">
          Selected:
        </Typography>
        <div style={{flex: 5}}>
          {selectedItems.map((p, id) =>
            <Chip key={id}
                  size={'small'}
                  avatar={<Avatar src={p.image ? p.image : 'default'}></Avatar>}
                  label={(p.title != null && p.title !== undefined) ? p.title.replace(/^(.{6}[^\s]*).*/, "$1") : ''}
                  onDelete={event => this.deleteItem(p)}
            />
          )}
        </div>
        <Button style={{flex: 1}} color="inherit" onClick={this.onOk}>Ok</Button>
      </Toolbar>
      }
    </Dialog>
  }

  async logError (message) {
    this.setState({alertOpen: true, alertMessage: message});
  }
}

export default ExtPickerDialog;


