import * as React from 'react';

interface DisplayProps {
  displayText: string;
}

const Display: React.FC<DisplayProps> = ({ displayText }) => (
  <div className='display'>{displayText}</div>
);

export { Display };
