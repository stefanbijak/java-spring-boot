import { useState, useEffect } from 'react';

function VlanList() {
  // 1. Stanje u kojem čuvamo listu VLAN-ova (početno je prazan niz)
  const [vlans, setVlans] = useState([]);
  // Stanja za učitavanje i greške (korisno za bolji UX)
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 2. useEffect se izvršava odmah nakon što se komponenta prikaže na ekranu
  useEffect(() => {
    fetch('http://localhost:8080/vlans')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Mrežna greška pri dohvatanju podataka');
        }
        return response.json(); // Prevaramo odgovor u JS objekat/niz
      })
      .then((data) => {
        setVlans(data);      // Spremamo podatke u stanje
        setLoading(false);   // Isključujemo poruku za učitavanje
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []); // Prazan niz [] znači da se ovo pokreće SAMO JEDNOM prilikom učitavanja

  // Prikaz dok se podaci još učitavaju
  if (loading) return <p>Učitavanje VLAN-ova...</p>;
  
  // Prikaz ako dođe do greške (npr. backend nije pokrenut)
  if (error) return <p style={{ color: 'red' }}>Greška: {error}</p>;

  // 3. Glavni prikaz kada su podaci spremni
  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h2>Lista VLAN-ova</h2>

      {/* Prikaz u obliku jednostavne tabele */}
      <table border="1" cellPadding="10" style={{ borderCollapse: 'collapse', width: '100%' }}>
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2' }}>
            <th>ID</th>
            <th>Broj (Number)</th>
            <th>Naziv (Name)</th>
            <th>Opis (Description)</th>
          </tr>
        </thead>
        <tbody>
          {/* Koristimo .map() za prikaz svakog VLAN-a */}
          {vlans.map((vlan) => (
            <tr key={vlan.id}>
              <td>{vlan.id}</td>
              <td><strong>{vlan.number}</strong></td>
              <td>{vlan.name}</td>
              <td>{vlan.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default VlanList;