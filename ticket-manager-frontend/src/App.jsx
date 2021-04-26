import './static/css/App.css';
import { 
  Switch,
  Route,
} from 'react-router-dom';
import Navigation from './router/Navigation'
import routes from './router/router-config'
import { 
  Container,
  Row
} from 'react-bootstrap';

function App() {
  return (
    <>
      <Container fluid className="defaultStyling" style={{padding: '0px'}}>
        <Row style={{width: '100%'}} noGutters>
          <header>
            <Navigation />
          </header>
        </Row>
          <main>
          <noscript>You need to enable JavaScript to run this app.</noscript>
            <Switch>
              {routes.map((route, i) => {
                return (
                  <Route 
                    key={i} 
                    path={route.path} 
                    exact={route.exact}
                    render={() => {
                      return route.component
                    }}/>
                )
              })}
            </Switch>
          </main>
        <Row>
          <footer>
            <p>Creditation</p><a href="https://www.freepik.com/vectors/background">Background vector created by starline - www.freepik.com</a>
          </footer>
        </Row>
      </Container>
    </>
  );
}

export default App;
