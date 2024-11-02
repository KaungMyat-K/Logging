import React, { useEffect } from 'react'
import Table from '../components/Table'
import Filter from '../components/Filter'
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchFilterItem } from '../slices/LogSlice';

export default function TablePage() {

  const {project} = useParams();

  const dispatcher = useDispatch();
  const data = useSelector(state => state.log.filterItem);

  useEffect(() => {
    dispatcher(fetchFilterItem(project))
  }, []);

  return (
    <>
      <Filter status={data.status} method={data.method} />
      <Table/>
    </>
  )
}
