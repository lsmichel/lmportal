import React, {  useState  } from 'react';
// import './headerLmPortal.scss';

import "../lmPortalSCSS/lmPortalSCSS.scss";
import "./homeSCSS.scss"
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col,
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem
} from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

// import {   } from "@fortawesome/free-solid-svg-icons";
// import {checkIfNotNull} from "../../shared/util/checkIfNotNull";



export   const LmHomePage=(props)=>{

  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);
return(
  <>
  {/** MENU **/}
  <Navbar className="customMenu" fixed="top"  light  expand="md">
        <NavbarBrand href="/">LM PORTAL </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="ml-auto" navbar>
            <NavItem className="menuItem">
              <NavLink  href="/components/">Connexion</NavLink>
            </NavItem>
            <NavItem>
              <NavLink className="menuItem" href="https://github.com/reactstrap/reactstrap">Inscription</NavLink>
            </NavItem>
            <NavItem>
              <NavLink className="menuItem" href="/home">jipHome</NavLink>
            </NavItem>
            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                Options
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem>
                  Option 1
                </DropdownItem>
                <DropdownItem>
                  Option 2
                </DropdownItem>
                <DropdownItem divider />
                <DropdownItem>
                  Reset
                </DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          {/* <NavbarText>Simple Text</NavbarText> */}
        </Collapse>
      </Navbar>
      {/** MENU **/}
      <section id="hero" className="container-fluid" style={{backgroundColor: "#eeee"}}>
        {/* <!-- Jumbotron Header --> */}
        <header className="jumbotron headerStyle">
            <div className="container">
                <div className="col-md-8">
                    <h1 id="mainHeader">La <span>digitalisation</span><br/> &agrave; la port&eacute;e de tous</h1>
                    <div className="headerDiv">
                        <p className="headerText">LM-PORTAL est une application de cr&eacute;ation et de gestion de site internet.
                            Vous n&apos;avez besoin d&apos;aucune comp&eacute;tences en programations pour cr&eacute;er un site dynamique ,rapide et moderne.
                            Rejoignez cette aventure digitale et d&eacute;cupl&eacute; le potentiel de votre activit&eacute; en remplissant le formulaire d&apos;inscription.
                        </p>
                    </div>
                    <div className="mainButton row">
                        <div className="col-md-3"><button className="btn btn-custom  btn-block">Inscrivez-vous ici</button></div> 
                        <div className="col-md-3"><button className="btn btn-customRouge  btn-block">Connexion</button></div> 
                    </div>
                </div>
            </div>
        </header>
    </section>
        {/* <!-- Jumbotron Header end --> */}

{/* <!---CREATING SITE STEP --> */}
<section className="container">
    <h2 id="about">Cr&eacute;er un site internet en 3 &eacute;tapes</h2>
    <hr className="separator centerElemt" />
    <br/>
    <div className="main-timeline">
        <div className="row container centerElemt">
            <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                <div className="timeline ">
                    <a href="#" className="timeline-content">
                        <div className="timeline-year">
                            <span>1</span>
                        </div>
                        <div className="inner-content">
                            <div className="timeline-icon">
                                <i className="fa fa-globe"></i>
                            </div>
                            <h3 className="title">Inscription</h3>
                            <p className="description">
                                Remplissez le formulaire d&apos;inscription, afin de creer votre compte
                                <br/>
                                <br/>
                            </p>
                        </div>
                    </a>
                </div>
            </article>
            <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                <div className="timeline orange">
                    <a href="#" className="timeline-content">
                        <div className="timeline-year">
                            <span>2</span>
                        </div>
                        <div className="inner-content">
                            <div className="timeline-icon">
                                <i className="fa fa-globe"></i>
                            </div>
                            <h3 className="title">Personalisation</h3>
                            <p className="description">
                                Ajouter des images , votre logo afin d&apos;obtenir le site de vos r&ecirc;ves , personnalisé le &agrave; votre guise
                                <br/>&nbsp;<br></br>
                                &nbsp;
                                <br/>
                            </p>
                        </div>
                    </a>
                </div>
            </article>
            <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                <div className="timeline green">
                    <a href="#" className="timeline-content">
                        <div className="timeline-year">
                            <span>3</span>
                        </div>
                        <div className="inner-content">
                            <div className="timeline-icon">
                                <i className="fa fa-users"></i>
                            </div>
                            <h3 className="title">Publication</h3>
                            <p className="description">
                                Une fois que vous avez personnalis&eacute; votre site internet , publiez le afin que 
                                vos potentiels clients puissent le visiter
                            </p>
                        </div>
                    </a>
                </div>
            </article>
        </div>
    </div>
</section>
{/* <!---CREATING SITE STEP END--> */}



    {/* <!-- end banner--> */}


    {/* <!--begin about--> */}
    
    <section className="container-fluid about">
        <div className="container">
            <h2 id="about">Creer un site en quelques cliques </h2>
            <hr className="separator" />
            <div className="row">
                <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                    <h3>Inscription</h3>
                    <p>Repondez aux question sur votre activit&eacute;. moins d&apos;une dizaines , vous pouvez modifier ces informations 
                        plus tard.</p>
                </article>
                <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                    <h3>10 minutes</h3>
                    <p>Il faudra en moyenne 10 minutes pour avoir un site op&eacute;rationnel et completement personnalis&eacute;</p>
                </article>
                <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                    <h3>10.000 FCFA</h3>
                    <p>Nos services sont accessible &agrave; partir de la modique somme de 10.000 FCFA</p>
                </article>
            </div>
            <div className="row" style={{marginBottom: "55px", marginTop: "25 px", paddingBottom :"30 px"}}>
                <div  className="col-md-6 offset-md-3" ><button style={{backgroundColor: "#3498db"}} className="btn btn-custom btn-block">Inscrivez-vous ici</button></div> 
            </div>
        </div>
       
    </section>
    {/* <!-- end about--> */}

    {/* <!--begin about--> */}
    <section className="container-fluid portfolio">
        <div className="container">
            <h2 id="portfolio">Les avantages d&apos;avoir un site</h2>
            <hr className="separator" />
            <div className="row">
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsdsd
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsds
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsds
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsds
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsd
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsd
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsd
                </article>
                <article className="col-md-3 col-lg-3 col-sm-12 col-xs-12 item-folio">
                    sdsd
                </article>
            </div>
        </div>
    </section>
    {/* <!--end about--> */}


    <footer id="footer" className="container-fluid footer">
        <div className="container">
            <div className="row"> 
                <div className="col-md-4 ">
                    <h2 id="lmPortal">LM-PORTAL</h2>

                    <p id="lmDescr">
                        <br/>
                            Nous facilitons la revolution digitale vous offrant une plateforme simple et moderne vous
                            permettant de developper votre site internet sans comp&eacute;tances en programation
                    </p>
                </div>
                <div className="col-md-4 col-footer">
                    <h2 id="suivezNous">Suivez-nous</h2>

                    <div id="suivezNous">
                        <br/>
                            Nous facilitons la revolution digitale vous offrant une plateforme simple et moderne vous
                            permettant de developper votre site internet sans comp&eacute;tances en programation
                    </div>
                </div>
                <div className="col-md-4 col-footer">
                    <h2 id="contact">Contact</h2>
                    <br/>

                    <form >
                        <div className="form-group">
                            <input placeholder="Nom" type="text" className="form-control" id="exampleInputEmail1" />
                        </div>
                        <div className="form-group">
                            <input placeholder="Email" type="email" className="form-control"  />
                        </div>
                        <div className="form-group">
                            <textarea rows={3} className="form-control" id="exampleInputPassword1"
                                placeholder="Message"></textarea>
                            <button type="submit" className="btn btn-primary customSubmit">Valider</button>
                       </div>
                    </form>
                </div>
            </div>

        </div>
        <p id="copy">Copyright 2020 LMPORTAL</p>

    </footer>
</>
)}

