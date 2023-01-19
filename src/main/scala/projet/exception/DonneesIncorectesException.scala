package projet.exception

final case class DonneesIncorectesException(private val message: String)
    extends Exception(message)
