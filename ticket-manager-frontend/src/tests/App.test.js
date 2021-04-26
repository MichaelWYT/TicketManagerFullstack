import { render, screen, mount } from '@testing-library/react';
import { expect } from '@testing-library/jest-dom/extend-expect'
import { BrowserRouter } from 'react-router-dom';
import App from '../App';
import { createRenderer } from 'react-dom/test-utils';
import { unmountComponentAtNode } from 'react-dom';

let container = null;

const setUp = () => {
    container = document.createElement("div");
    document.body.appendChild(container);
}

const teardown = () => {
    unmountComponentAtNode(container);
    container.remove();
    container = null;
}

test('Render app page', () => {
  render(<BrowserRouter><App/></BrowserRouter>)
  const linkElement = screen.getByTestId(/Helping you with your ticket needs/i);
  expect(linkElement).toBeInTheDocument();
})

// describe('Render routes from App', () => {
//   const wrapper = mount(
//     <BrowserRouter>
//       <App/>
//     </BrowserRouter>
//   )

//   expect(wrapper.find(Landing)).toBe(<Landing/>)
// })
