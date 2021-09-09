import * as React from 'react';

import 'normalize.css';

import './App.css';
import { Button } from './components/Button';
import { ButtonContainer } from './components/ButtonContainer';
import { Container } from './components/Container';
import { Display } from './components/Display';

export const App: React.FC = () => {
  const [displayText] = React.useState('0');

  return (
    <Container>
      <Display displayText={displayText} />
      <ButtonContainer>
        <Button className='key-clear function-key'>C</Button>
        <Button className='key-clear-current function-key'>CE</Button>
        <Button className='key-signal function-key'>&plusmn;</Button>
        <Button className='key-divide command-key'>&divide;</Button>
        <Button className='key-multiply command-key'>&times;</Button>
        <Button className='key-subtract command-key'>&minus;</Button>
        <Button className='key-add command-key'>+</Button>
        <Button className='key-equal'>=</Button>
        <Button className='key-9 digit-key'>9</Button>
        <Button className='key-8 digit-key'>8</Button>
        <Button className='key-7 digit-key'>7</Button>
        <Button className='key-6 digit-key'>6</Button>
        <Button className='key-5 digit-key'>5</Button>
        <Button className='key-4 digit-key'>4</Button>
        <Button className='key-3 digit-key'>3</Button>
        <Button className='key-2 digit-key'>2</Button>
        <Button className='key-1 digit-key'>1</Button>
        <Button className='key-0 digit-key'>0</Button>
        <Button className='key-dot digit-key'>.</Button>
      </ButtonContainer>
    </Container>
  );
};
