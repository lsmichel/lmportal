import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';
import React from 'react';
import "../lmPortalCSS/lmPortalCSS.scss"
import {HeaderLmPortal} from "../headerLmPortal/headerLmPortal";
export default class LmHomePage  extends React.Component{
    render(){
    return(
        <>
    {/* <header className="container-fluid header">
    <div className="container">
        <a href="#" className="logo">LM PORTAL</a>
    </div>
    <nav className="menu">
        <a href="#">Accueil</a>
        <a href="#about">&Agrave; propos</a>
        <a href="#portfolio">Porfolio</a>
        <a href="#contact">Contact</a>
    </nav>
</header> */}
<HeaderLmPortal/>
<section className="container-fluid banner">
    <div className="ban">
                <img src="../../../content/images//topIllustration.svg" alt="banniere du site"/>
            </div>
        {/* </div>
    </div> */}
    
</section>

<section>
    <div className="container" id="headerBottom">
        <div className="inner-banner">
            <h1>Bienvenu sur LM PORTAL</h1>
            <p>Creer votre site professionnelle facilement,
               <br/> juste en repondant aux questions
            </p>
            <button className="btn btn-custom"> Inscrivez-vous ici ! </button>                         
            
            <button className="btn btn-custom"> Connexion</button>
        </div>
     </div>
</section>
<section  className="container-fluid about">
    <div className="container">
        <h2 id="about">&Agrave; propos</h2>
        <hr className="separator"/>
        <div className="row">
            <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                <h2>&Eacute;tudes</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam et neque labore maxime ipsa quaerat
                    corporis dolor ullam eos atque nostrum hic est eaque aperiam, magnam quisquam non eum iste?</p>
            </article>
            <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                <h2>Experience</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam et neque labore maxime ipsa quaerat
                    corporis dolor ullam eos atque nostrum hic est eaque aperiam, magnam quisquam non eum iste?</p>
            </article>
            <article className="col-md-4 col-lg-4 col-sm-12 col-xs-12">
                <h2>Hobbies</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam et neque labore maxime ipsa quaerat
                    corporis dolor ullam eos atque nostrum hic est eaque aperiam, magnam quisquam non eum iste?</p>
            </article>
        </div>
    </div>
</section>

<section className="container-fluid portfolio">
    <div className="container">
        <h2 id="portfolio">Mon portfolio</h2>
        <hr className="separator"/>
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
<footer className="container-fluid footer">
    <div className="container">
        <h2 id="contact">Contact</h2>
        <hr className="separator"/>
        <form>
            <div className="form-group">
              <input placeholder="Nom" type="text" className="form-control" id="exampleInputEmail1"/>
            </div>
            <div className="form-group">
                <input placeholder="Email" type="email" className="form-control"/>
              </div>
            <div className="form-group">
              <textarea rows={5} className="form-control" id="exampleInputPassword1" placeholder="Message"></textarea>
            <button type="submit" className="btn btn-primary customSubmit">Valider</button>
              </div>
          </form>
    </div>
    <p>Copyright 2020 LMPORTAL</p>
</footer>
</>)

}

}