
import React, { useEffect } from 'react';
import './tenant.scss';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import {   } from "@fortawesome/free-solid-svg-icons";
import {checkIfNotNull} from "../../shared/util/checkIfNotNull";



export  const DynamicFooter =(props)=>{
    return(
	<section id="footerCustom"> 
		<div className="container">
			<div className="row text-center text-xs-center text-sm-left text-md-left">
				<div className="col-xs-12 col-sm-6 col-md-6 text-white">
					<h5>Contacts</h5>
					<ul className="list-unstyled quick-links">
            <li><div>Num&eacute;ro de t&eacute;l&eacute;phone : &nbsp;{checkIfNotNull(props.numeroTenant)}</div></li>
						<li><div>Location</div></li>
						<li><div >Email : {checkIfNotNull(props.emailTenant)}</div></li>
						{/* <li><div >Videos</div></li> */}
					</ul>
				</div>
				<div className="col-xs-12 col-sm-6 col-md-6">
					<h5>Rejoignez nous sur les reseaux sociaux</h5>
          <Row  className="list-unstyled quick-links">
            <Col style={{height:"45px"}} md="2">
            <a href={checkIfNotNull(props.facebookTenant)} ><span className="facebook">&nbsp;</span></a>
            </Col>
          <Col md="2">
              <a href={checkIfNotNull(props.instagramTenant)} ><span className="instagram">&nbsp;</span></a>
          </Col>
         <Col md="2">
            <a href={checkIfNotNull(props.youtubeTenant)} ><span className="youtube">&nbsp;</span></a>
          </Col>
         <Col md="2">
             <a href={checkIfNotNull(props.twitterTenant)} ><span className="twitter">&nbsp;</span></a>
          </Col>
          </Row>
				</div>
			</div>
			<div>
			</div>	
			<div className="row">
				<div className="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-2 text-center text-white">
					<p>{props.spaceNameTenant}</p>

          <br/>
					<p className="h6">Conception : LmPortal</p>
      	</div>
				<hr/>
			</div>	
		</div>
	</section>

      )
      }

