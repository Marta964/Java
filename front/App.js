import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import HeaderComponent from './components/HeaderComponent';
import ListComponent from './components/ListComponent';
import { BrowserRouter as Router,Route,Routes} from 'react-router-dom';
import AddRate from './rates/AddRate';
import EditRate from './rates/EditRate';
import AddConversion from './rates/AddConversion';
import ViewRatee from './rates/ViewRatee';


function App() {
  return (
    <div className="App">
    <Router>
    <HeaderComponent/>
    
    <Routes>
      <Route exact path="/" element = {<ListComponent/>}></Route>
      <Route exact path="/add" element = {<AddRate/>}></Route>
      <Route exact path="addConv/:id" element = {<AddConversion/>}></Route>
      <Route exact path="/update/:id" element = {<EditRate/>}></Route>
      <Route exact path="view/:id" element={<ViewRatee/>}></Route>
    </Routes>
    </Router>
    </div>
  );
}

export default App;
