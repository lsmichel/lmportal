import React, { useEffect } from 'react';
import './tenant.scss';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import {   } from "@fortawesome/free-solid-svg-icons";
import {checkIfNotNull} from "../../shared/util/checkIfNotNull";



export  const DynamicHeader=(props)=>{
return(
<nav style={{left:0,right:0,backgroundColor:"pink"}} id="headerCustom">
  <div className="container">
    <a className="navbar-brand" href="#">Navbar</a>
  </div>
</nav>

)}

/* <nav style={{}} className="navbar navbar-expand-lg navbar-dark bg-dark">
  <a className="navbar-brand" href="#">Navbar</a>
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div className="navbar-nav">
      <a className="nav-item nav-link active" href="#">Home <span className="sr-only">(current)</span></a>
      <a className="nav-item nav-link" href="#">Features</a>
      <a className="nav-item nav-link" href="#">Pricing</a>
    </div>
  </div>
</nav> */